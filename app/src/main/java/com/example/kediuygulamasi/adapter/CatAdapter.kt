package com.example.kediuygulamasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kediuygulamasi.R
import com.example.kediuygulamasi.model.Cat
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
        //image ve favorite kısmı biraz farklı, o yüzden onları sonraya bıraktım. favoritede "if" sorgusu, image'da glide kullancam.
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    fun searchedList(newCatList : List<Cat>){ //search yaptığımızda gelecek olan listeyi catList'e koyduk.
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged() //adapter'ü yenilemek için bir method
    }
}