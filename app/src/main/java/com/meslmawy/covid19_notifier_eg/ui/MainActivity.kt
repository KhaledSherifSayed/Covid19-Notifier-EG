package com.meslmawy.covid19_notifier_eg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import androidx.work.*
import com.meslmawy.covid19_notifier_eg.databinding.ActivityMainBinding
import com.meslmawy.covid19_notifier_eg.ui.adapter.StateAdapter
import com.meslmawy.covid19_notifier_eg.ui.adapter.TotalAdapter
import com.meslmawy.covid19_notifier_eg.utils.State
import com.meslmawy.covid19_notifier_eg.worker.NotificationWorker
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.TimeUnit
import com.meslmawy.covid19_notifier_eg.R


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val mTotalAdapter = TotalAdapter()
    private val mStateAdapter = StateAdapter()
    private val adapter = MergeAdapter(mTotalAdapter, mStateAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Init Toolbar
        setSupportActionBar(binding.toolbar)

        // Set adapter to the RecyclerView
        binding.recycler.adapter = adapter

        initData()
        initWorker()

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun initData() {
        viewModel.covidLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> binding.swipeRefreshLayout.isRefreshing = true
                is State.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val list = state.data.historyDataList
                    mTotalAdapter.submitList(list.subList(0, 1))
                    mStateAdapter.submitList(list.subList(1, list.size - 1))
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })
        loadData()
    }

    private fun loadData() {
        viewModel.getData()
    }

    private fun initWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val notificationWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            JOB_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }

    companion object {
        const val JOB_TAG = "notificationWorkTag"
    }

}
