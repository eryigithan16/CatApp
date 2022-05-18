package com.example.kediuygulamasi.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.service.CatDatabase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : BaseViewModel(application){

    private val disposable = CompositeDisposable()

    val favCats = MutableLiveData<List<Cat>>()
    val favCatError = MutableLiveData<Boolean>()
    val favCatLoading = MutableLiveData<Boolean>()

    private fun showCats(catList : List<Cat>){
        favCats.value = catList
        favCatError.value = false
        favCatLoading.value = false
    }

    fun getFavList(){
        launch {
            favCats.value = CatDatabase(getApplication()).catDao().getFavCats()
            showCats(favCats.value!!)
            Log.e("@@@@@@", favCats.value.toString())
            Toast.makeText(getApplication(),"Fav Cats from Sql" + favCats.value!!.size, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}