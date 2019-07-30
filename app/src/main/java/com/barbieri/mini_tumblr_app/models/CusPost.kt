package com.barbieri.mini_tumblr_app.models

class CusPost {

    var description:String? = ""
    var title:String? = ""
    var url:String? = ""
    var redirectUrl:String? = ""
    override fun toString(): String {
        return "CusPost(description=$description, title=$title, url=$url, redirectUrl=$redirectUrl)"
    }


}