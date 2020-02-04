package kz.nura.zapistestapp.viewmodels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import kz.nura.zapistestapp.SalonRepository
import kz.nura.zapistestapp.domain.Salon

class HomeViewModel : BaseViewModel() {

    private val repository = SalonRepository()
    val salons: LiveData<List<Salon>> = repository.salons

    init {
        mainScope.launch {
            repository.loadSalons()
        }
    }



}