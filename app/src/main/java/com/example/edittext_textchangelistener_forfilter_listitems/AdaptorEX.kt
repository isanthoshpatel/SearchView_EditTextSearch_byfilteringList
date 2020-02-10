package com.example.edittext_textchangelistener_forfilter_listitems

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView

class AdaptorEX(var c:Context,var list:MutableList<CardviewData>):RecyclerView.Adapter<AdaptorEX.ViewHolderEx>() {



    class ViewHolderEx : RecyclerView.ViewHolder {

        var tv_title:TextView? = null
        var tv_desc:TextView? = null

        constructor(v: View):super(v){
            tv_title = v.findViewById(R.id.tv1)
            tv_desc = v.findViewById(R.id.tv2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEx {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return ViewHolderEx(v)
    }

    override fun onBindViewHolder(holder: ViewHolderEx, position: Int) {
        var item = list.get(position)
        holder.apply {
            tv_title?.text = item.title
            tv_desc?.text = item.desc
        }
    }

    override fun getItemCount(): Int = list.size
}