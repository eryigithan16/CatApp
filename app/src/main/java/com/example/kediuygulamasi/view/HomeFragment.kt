package com.example.kediuygulamasi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.adapter.CatAdapter
import com.example.kediuygulamasi.service.CatDatabase
import com.example.kediuygulamasi.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_cat.*

class HomeFragment : Fragment() {

    lateinit var viewModel : HomeViewModel
    private val catAdapter = CatAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.showData()

        iv_home_SearchBtn.setOnClickListener {
            var currentText = etSearch.text.toString()
            Log.e("currentText", currentText)
            if (currentText.isEmpty()){
                viewModel.getListDataFromApi()
            }
            else{
                viewModel.getSearchedListDataFromApi(currentText)
            }
        }

        iv_home_Favorites.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
            Navigation.findNavController(it).navigate(action)
        }

        rvCatList.layoutManager = LinearLayoutManager(context)
        rvCatList.adapter = catAdapter
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.cats.observe(viewLifecycleOwner, Observer {
            it?.let {
                var i = 0
                while (i < it.size){
                    if (it[i].catIsFavorited == true){
                        viewModel.intoFavList(it[i])
                    }
                    else if(it[i].catIsFavorited == false){
                        viewModel.deleteFromFavlist(it[i])
                    }
                    i = i+1
                }
                rvCatList.visibility = View.VISIBLE
                catAdapter.updatedList(it)
            }
        })

        viewModel.catError.observe(viewLifecycleOwner, Observer {
            if (it){
                tvError.visibility = View.VISIBLE
                rvCatList.visibility = View.GONE
                pbLoading.visibility = View.GONE
            }
            else{
                tvError.visibility = View.GONE
            }
        })

        viewModel.catLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                tvError.visibility = View.GONE
                rvCatList.visibility = View.GONE
                pbLoading.visibility = View.VISIBLE
            }
            else{
                pbLoading.visibility = View.GONE
            }
        })
    }

}