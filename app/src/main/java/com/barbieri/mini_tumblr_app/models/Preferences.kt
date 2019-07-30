package com.barbieri.mini_tumblr_app.models

class Preferences {

    var cus_photos_post = mutableListOf<CusPost>()
    var cus_videos_post = mutableListOf<CusPost>()
    var cus_texts_post = mutableListOf<CusPost>()
    override fun toString(): String {
        return "Preferences(cus_photos_post=$cus_photos_post, cus_videos_post=$cus_videos_post)"
    }


}