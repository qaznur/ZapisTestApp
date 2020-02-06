package kz.nura.zapistestapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import kz.nura.zapistestapp.SalonRepository
import kz.nura.zapistestapp.domain.Salon

class HomeViewModel(application: Application) : BaseViewModel() {

    private val repository = SalonRepository(application)
    val salons: LiveData<List<Salon>> = repository.salons

    private val _exception: MutableLiveData<Exception> = MutableLiveData()
    val exception: LiveData<Exception>
        get() = _exception

    init {
        refresh()
    }

    fun refresh() {
        mainScope.launch {
            try {
                repository.loadSalons()
            } catch (ex: Exception) {
                _exception.value = ex
            }
        }
    }

    fun onSetNoException() {
        _exception.value = null
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}