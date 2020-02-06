package kz.nura.zapistestapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.nura.zapistestapp.domain.Salon
import kz.nura.zapistestapp.domain.SalonDetail
import kz.nura.zapistestapp.network.AbstractSafeApiRequest
import kz.nura.zapistestapp.network.Network
import kz.nura.zapistestapp.network.asDomainModel

class SalonRepository(application: Application) : AbstractSafeApiRequest(application) {

    private val _salons: MutableLiveData<List<Salon>> = MutableLiveData()
    val salons: LiveData<List<Salon>>
        get() = _salons

    private val _salonDetail: MutableLiveData<SalonDetail> = MutableLiveData()
    val salonDetail: LiveData<SalonDetail>
        get() = _salonDetail


    suspend fun loadSalons() {
        withContext(Dispatchers.IO) {
            val response = apiRequest { Network.apiService.getPopularSalons() }
            _salons.postValue(response?.asDomainModel())
            Log.d("###", "repo salons: $salons")
        }
    }

    suspend fun loadSalon(id: Long) {
        withContext(Dispatchers.IO) {
            val response = Network.apiService.getSalon(id).await()
            _salonDetail.postValue(response.asDomainModel())
            Log.d("###", "repo salonDetail: $salonDetail")
        }
    }
}