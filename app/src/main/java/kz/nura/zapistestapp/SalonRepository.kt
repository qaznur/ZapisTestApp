package kz.nura.zapistestapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.nura.zapistestapp.domain.Salon
import kz.nura.zapistestapp.network.Network
import kz.nura.zapistestapp.network.asDomainModel

class SalonRepository {

    private val _salons: MutableLiveData<List<Salon>> = MutableLiveData()
    val salons: LiveData<List<Salon>>
        get() = _salons


    suspend fun loadSalons() {
        withContext(Dispatchers.IO) {
            Log.d("###", "repo salons: ${salons.toString()}")
            val response = Network.apiService.getPopularSalons().await()
            _salons.postValue(response.asDomainModel())
        }
    }
}