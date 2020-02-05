package kz.nura.zapistestapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch
import kz.nura.zapistestapp.SalonRepository
import kz.nura.zapistestapp.domain.SalonDetail

class DetailsViewModel(id: Long?) : BaseViewModel() {

    private val repository = SalonRepository()
    val salonDetail: LiveData<SalonDetail> = repository.salonDetail
    private val _imageUrls: MutableLiveData<List<String>> = MutableLiveData()
    val imageUrls: LiveData<List<String>>
        get() = _imageUrls

    init {
        mainScope.launch {
            id?.let {
                repository.loadSalon(id)
                _imageUrls.value = salonDetail.value?.pictures
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("###", "onCleared")
    }

    class Factory(private val id: Long?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}