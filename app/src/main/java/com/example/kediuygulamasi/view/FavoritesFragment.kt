package com.example.kediuygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.adapter.FavoriteCatAdapter
import com.example.kediuygulamasi.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavoritesFragment : Fragment() {

    private lateinit var viewModel : FavoritesViewModel
    private val favoriteCatAdapter = FavoriteCatAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        rvFavCatList.layoutManager = LinearLayoutManager(context)
        rvFavCatList.adapter = favoriteCatAdapter
        viewModel.getFavList()

        iv_favorites_BackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.favCats.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoriteCatAdapter.updatedFavList(it)
                rvFavCatList.visibility = View.VISIBLE

            }
        })

        viewModel.favCatError.observe(viewLifecycleOwner, Observer {
            if (it){
                tvFavCatError.visibility = View.VISIBLE
                rvFavCatList.visibility = View.GONE
                pbFavLoading.visibility = View.GONE
            }
            else{
                tvFavCatError.visibility = View.GONE
            }
        })

        viewModel.favCatLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                tvFavCatError.visibility = View.GONE
                rvFavCatList.visibility = View.GONE
                pbFavLoading.visibility = View.VISIBLE
            }
            else{
                pbFavLoading.visibility = View.GONE
            }
        })
    }
}