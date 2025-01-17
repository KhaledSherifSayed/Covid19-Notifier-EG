
import android.util.Log
import androidx.annotation.MainThread
import com.meslmawy.covid19_notifier_eg.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import retrofit2.Response

@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<T> {

    fun asFlow() = flow<State<T>> {

        // Emit Loading State
        emit(State.loading())

        try {
            // Fetch latest data from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remotePosts = apiResponse.body()

            // Check for response validation
            if (apiResponse.isSuccessful && remotePosts != null) {
                emit(State.success(remotePosts))
            } else {
                // Something went wrong! Emit Error state.
                emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            //emit(State.error("Network error! Can't get latest data."))
            Log.d("Exception msg",e.message.toString())
            emit(State.error(e.message.toString()))
            e.printStackTrace()
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<T>
}