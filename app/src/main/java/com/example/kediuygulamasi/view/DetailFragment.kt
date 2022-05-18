package com.example.kediuygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.util.getImageFromUrl
import com.example.kediuygulamasi.util.placeholderProgessBar
import com.example.kediuygulamasi.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel
    lateinit var selectedCat : Cat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        arguments?.let { // bu satır; eğer arguments'in içi doluysa demek oluyor.
            selectedCat = DetailFragmentArgs.fromBundle(it).catItem!!
        }
        viewModel.getDataFromRoom(selectedCat)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.cat.observe(viewLifecycleOwner, Observer {
            tv_detail_CatName.text = it.catName
            tv_detail_CatLifeSpan.text = it.catLifeSpan
            tv_detail_CatOrigin.text = it.catOrigin
            tv_detail_CatWikiUrl.text = it.catWikiUrl
            tv_detail_Describtion.text = it.catDescription
            tv_detail_DogFriendly.text = it.catDogFriendly
            iv_detail_CatImage.getImageFromUrl(it.catImage?.url, placeholderProgessBar(requireContext()))

            if (it.catIsFavorited == true){
                iv_detail_AddFavBtn.setImageResource(R.drawable.ic_favorited)
            }
        })
    }
}