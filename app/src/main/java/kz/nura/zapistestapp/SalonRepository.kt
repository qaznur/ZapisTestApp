package kz.nura.zapistestapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.nura.zapistestapp.domain.Salon
import kz.nura.zapistestapp.domain.SalonDetail
import kz.nura.zapistestapp.network.Network
import kz.nura.zapistestapp.network.asDomainModel

class SalonRepository {

    private val _salons: MutableLiveData<List<Salon>> = MutableLiveData()
    val salons: LiveData<List<Salon>>
        get() = _salons

    private val _salonDetail: MutableLiveData<SalonDetail> = MutableLiveData()
    val salonDetail: LiveData<SalonDetail>
        get() = _salonDetail


    suspend fun loadSalons() {
        withContext(Dispatchers.IO) {
            Log.d("###", "repo salons: ${salons.toString()}")
            val response = Network.apiService.getPopularSalons().await()
            _salons.postValue(response.asDomainModel())
        }
    }

    suspend fun loadSalon(id: Long) {
        withContext(Dispatchers.IO) {
            Log.d("###", "repo salonDetail: ${salonDetail.toString()}")
            val response = Network.apiService.getSalon(id).await()
            _salonDetail.postValue(response.asDomainModel())
        }
    }
}