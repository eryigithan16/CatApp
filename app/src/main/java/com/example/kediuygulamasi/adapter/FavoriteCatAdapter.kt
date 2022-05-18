package com.example.kediuygulamasi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.util.getImageFromUrl
import com.example.kediuygulamasi.util.placeholderProgessBar

import kotlinx.android.synthetic.main.item_fav_cat.view.*

class FavoriteCatAdapter(val favList: ArrayList<Cat>):RecyclerView.Adapter<FavoriteCatAdapter.FavoriteCatViewHolder>() {
    class FavoriteCatViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_fav_cat, parent, false)
        return FavoriteCatViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteCatViewHolder, position: Int) {
        holder.view.tv_fav_item_CatName.text = favList[position].catName
        if (favList[position].catImage != null){
            holder.view.iv_fav_item_CatImage.getImageFromUrl(favList[position].catImage?.url, placeholderProgessBar(holder.view.context))
        }
        if (favList[position].catIsFavorited==true){
            holder.view.iv_fav_item_AddFavBtn.setImageResource(R.drawable.ic_favorited)
        }
        holder.view.iv_fav_item_AddFavBtn.setOnClickListener { //bunun içine silmek için dao fonksiyonu koyulcak
            if (favList[position].catIsFavorited==true){
                favList[position].catIsFavorited = false
                holder.view.iv_fav_item_AddFavBtn.setImageResource(R.drawable.ic_empty_star)
            }
            else{
                favList[position].catIsFavorited = true
                holder.view.iv_fav_item_AddFavBtn.setImageResource(R.drawable.ic_favorited)

            }
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    fun updatedFavList(newFavList : List<Cat>){ //search yaptığımızda gelecek olan listeyi catList'e koyduk.
        favList.clear()
        favList.addAll(newFavList)
        notifyDataSetChanged() //adapter'ü yenilemek için bir method
    }
}