
package com.meslmawy.covid19_notifier_eg.ui.adapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.meslmawy.covid19_notifier_eg.R
import com.meslmawy.covid19_notifier_eg.model.ResponseItem
import com.meslmawy.covid19_notifier_eg.utils.getPeriod
import java.text.SimpleDateFormat


@BindingAdapter("totalconfirmedText")
fun bindtotalconfirmedtext(textConfirmedTextView: TextView, item: ResponseItem?) {
    if (item != null) {
        textConfirmedTextView.text = item.total.toString()
    }
}

@BindingAdapter("totalrecoverdText")
fun bindtotalrecoverdtext(textRecoverdTextView: TextView, item: ResponseItem?) {
    if (item != null) {
        textRecoverdTextView.text = item.cases.recovered.toString()
    }
}

@BindingAdapter("totalactiveText")
fun bindtotalactivetext(textActiveTextView: TextView, item: ResponseItem?) {
    if (item != null) {
        textActiveTextView.text = item.cases.active.toString()
    }
}

@BindingAdapter("totaldeathesText")
fun bindtotaldeathtext(textDeathTextView: TextView, item: ResponseItem?) {
    if (item != null) {
        textDeathTextView.text = item.deaths.total.toString()
    }
}


@BindingAdapter("lastupdatetext")
fun bindlastupdatetext(lastupdateTextView: TextView, item: ResponseItem?) {
    if (item != null) {
        lastupdateTextView.text = lastupdateTextView.context.getString(
            R.string.text_last_updated,
            getPeriod(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .parse(item.lastUpdatedTime)))
    }
}



@BindingAdapter("confirmedimageViewvisible")
fun bindimageviewconfirmed(ImageView: ImageView, item: ResponseItem?) {
    item?.cases?.new?.let{
        if (it == "0")
            ImageView.visibility = View.GONE
        else
            ImageView.visibility = View.VISIBLE

    }
}

@BindingAdapter("bindtextconfirmedplus")
fun bindtextconfirmedplus(TextView: TextView, item: ResponseItem?) {
    item?.cases?.new?.let{
        if (it != "0")
            TextView.text = item.cases.new
    }
}

@BindingAdapter("bindimageviewdeathes")
fun bindimageviewdeathes(ImageView: ImageView, item: ResponseItem?) {
    item?.deaths?.new?.let{
        if (it == "0")
            ImageView.visibility = View.GONE
        else
            ImageView.visibility = View.VISIBLE

    }
}

@BindingAdapter("bindtextdeathsplus")
fun bindtextdeathsplus(TextView: TextView, item: ResponseItem?) {
    item?.deaths?.new?.let{
        if (it != "0")
            TextView.text = item.deaths.new
    }
}