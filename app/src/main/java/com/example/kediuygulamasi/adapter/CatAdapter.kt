package com.example.kediuygulamasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.util.getImageFromUrl
import com.example.kediuygulamasi.util.placeholderProgessBar
import com.example.kediuygulamasi.view.HomeFragment
import com.example.kediuygulamasi.view.HomeFragmentDirections
import com.example.kediuygulamasi.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.item_cat.view.*



class CatAdapter(val catList: ArrayList<Cat>):RecyclerView.Adapter<CatAdapter.CatViewHolder>() {


    class CatViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.view.tv_item_CatName.text = catList[position].catName
        if (catList[position].catImage != null){
            holder.view.iv_item_CatImage.getImageFromUrl(catList[position].catImage?.url, placeholderProgessBar(holder.view.context))
            //Log.e("@@@@@1", catList[position].catImage?.url.toString())
        }
        //favoriteye tıklanınca şu olcak tarzı bi onClick de yazılabilir aşşağıdaki gibi if de olabilir sonra bak.
        if (catList[position].catIsFavorited==true){
            holder.view.iv_item_AddFavBtn.setImageResource(R.drawable.ic_favorited)
        }
        holder.view.iv_item_AddFavBtn.setOnClickListener {
            if (catList[position].catIsFavorited==true){
                catList[position].catIsFavorited = false
                holder.view.iv_item_AddFavBtn.setImageResource(R.drawable.ic_empty_star)
            }
            else{
                catList[position].catIsFavorited = true
                holder.view.iv_item_AddFavBtn.setImageResource(R.drawable.ic_favorited)
                //HomeFragment().viewModel.intoFavList(catList[position])
            }
        }

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(catList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    fun updatedList(newCatList : List<Cat>){ //search yaptığımızda gelecek olan listeyi catList'e koyduk.
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged() //adapter'ü yenilemek için bir method
    }
}