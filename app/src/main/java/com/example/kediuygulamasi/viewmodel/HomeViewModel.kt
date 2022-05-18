package com.example.kediuygulamasi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.service.CatApiService
import com.example.kediuygulamasi.service.CatDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val catApiService = CatApiService()
    private val disposable = CompositeDisposable()

    val cats = MutableLiveData<List<Cat>>()
    val catError = MutableLiveData<Boolean>()
    val catLoading = MutableLiveData<Boolean>()
    // val catIsFavorited = MutableLiveData<Boolean>()

    var handler = 1

    fun showData(){
        if (handler == 1){
            getListDataFromApi()
            handler = 2
        }
        else{
            getListDataFromSql()
        }
    }

    fun getListDataFromApi() {
        catLoading.value = true
        disposable.add(
            catApiService.getCatListDataFromApi()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cat>>(){
                    override fun onSuccess(t: List<Cat>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Cats from Api",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        catLoading.value = false
                        catError.value = true
                    }

                })
        )
    }

    fun getSearchedListDataFromApi(searchCat : String?){
        catLoading.value = true
        disposable.add(
            catApiService.getSearchedCatListFromApi(searchCat)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cat>>(){
                    override fun onSuccess(t: List<Cat>) {
                        showCats(t)
                    }

                    override fun onError(e: Throwable) {
                        catLoading.value = false
                        catError.value = true
                    }

                })
        )
    }

    private fun showCats(catList : List<Cat>){
        cats.value = catList
        catError.value = false
        catLoading.value = false
    }

    private fun storeInSQLite(list: List<Cat>){
        launch {
            val dao = CatDatabase(getApplication()).catDao()
            val listLong = dao.insertAll(*list.toTypedArray()) //listeki elemanları tek tek ekler
            var i = 0
            while (i < list.size){
                list[i].catId = listLong[i].toInt()
                i = i+1
            }
            showCats(list)
        }
    }

    fun getListDataFromSql(){
        launch {
            val cats = CatDatabase(getApplication()).catDao().getAllCats()
            showCats(cats)
            Toast.makeText(getApplication(),"Cats from Sql",Toast.LENGTH_LONG).show()
        }
    }

    fun intoFavList(cat: Cat){
        launch(Dispatchers.IO) {
            CatDatabase(getApplication()).catDao().insertToFavorites(cat)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}