package com.barbieri.mini_tumblr_app.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barbieri.mini_tumblr_app.models.CusPost
import com.barbieri.mini_tumblr_app.R

class AdapterTextPost (var list: MutableList<CusPost>?) : RecyclerView.Adapter<AdapterTextPost.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_tumblr_posts_3, parent, false)
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
            val title:String? = data.title

            println("\nPost: $info, $url")
            val tv_info: TextView = itemView.findViewById(R.id.tv_info)
            val tv_post: TextView = itemView.findViewById(R.id.tv_post)
            val iv_gotoweb: ImageView = itemView.findViewById(R.id.iv_gotoweb)

            tv_info.text = title
            tv_post.text = info

            iv_gotoweb.setOnClickListener {
                val browse = Intent(Intent.ACTION_VIEW, Uri.parse(data.redirectUrl))
                itemView.context.startActivity(browse)
            }


        }

    }

}