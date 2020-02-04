package kz.nura.zapistestapp.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel: ViewModel() {

    private val job = Job()
    protected val mainScope = CoroutineScope(job + Dispatchers.Main)
    protected val ioScope = CoroutineScope(job + Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }
}