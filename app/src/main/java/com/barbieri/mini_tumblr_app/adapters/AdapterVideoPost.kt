package com.barbieri.mini_tumblr_app.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.barbieri.mini_tumblr_app.R
import com.barbieri.mini_tumblr_app.models.CusPost
import com.squareup.picasso.Picasso

class AdapterVideoPost(var list: MutableList<CusPost>?) : RecyclerView.Adapter<AdapterVideoPost.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_tumblr_posts_2, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list!![position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(data: CusPost) {

            val url = data.url
            val info = data.description

            println("\nPost: $info, $url")
            val tv_info: TextView = itemView.findViewById(R.id.tv_info)
            val iv_gotoweb: ImageView = itemView.findViewById(R.id.iv_gotoweb)

            tv_info.text = info

            iv_gotoweb.setOnClickListener {
                val browse = Intent(Intent.ACTION_VIEW, Uri.parse(data.redirectUrl))
                itemView.context.startActivity(browse)
            }

        }

    }


}