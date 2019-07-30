package com.barbieri.mini_tumblr_app.fragments


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.barbieri.mini_tumblr_app.R
import com.barbieri.mini_tumblr_app.adapters.AdapterPhotoPost
import com.barbieri.mini_tumblr_app.models.CusPost
import com.barbieri.mini_tumblr_app.utils.Constants.MY_USER_PREF
import com.barbieri.mini_tumblr_app.utils.Constants.PRE_TUMBLR_URL
import com.barbieri.mini_tumblr_app.utils.Constants.NUM_POST_SAVED
import com.barbieri.mini_tumblr_app.utils.Constants.CONSUMERKEY
import com.barbieri.mini_tumblr_app.utils.Constants.CONSUMERSECRET
import com.barbieri.mini_tumblr_app.utils.Constants.TOKEN
import com.barbieri.mini_tumblr_app.utils.Constants.TOKENSECRET
import com.barbieri.mini_tumblr_app.utils.PreferencesUtils.getMyUserPref
import com.barbieri.mini_tumblr_app.utils.PreferencesUtils.setMyUserPref
import com.barbieri.mini_tumblr_app.utils.SecondaryMethods.isNetworkAvailable
import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.exceptions.JumblrException
import com.tumblr.jumblr.types.PhotoPost
import com.tumblr.jumblr.types.Post
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.fragment_photos.swipeRefresh
import kotlinx.android.synthetic.main.fragment_text.*


class PhotosFragment : Fragment() {

    var usertumblrBlog = "phyros1407"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFunction()

    }

    override fun onResume() {
        super.onResume()

        swipeRefresh?.setOnRefreshListener { mainFunction() }
        swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorPrimaryLight,
            R.color.colorPrimaryDark,
            R.color.secondaryText
        )


    }

    fun mainFunction(){
        if (isNetworkAvailable(context!!)) {

            try {
                Log.d("statusinternet", "Tiene internet")
                LongOperation().execute("")

            } catch (ex: JumblrException) {
                swipeRefresh?.isRefreshing = false
                Log.e("ERROR", ex.message)
                Log.d("statusinternet", "No tiene internet")
                var tempPreferences = getMyUserPref(context!!, MY_USER_PREF)
                setRecyclerViewElements(tempPreferences?.cus_photos_post)
            }

        } else {
            swipeRefresh?.isRefreshing = false
            Log.d("status_internet", "No tiene internet")
            var tempPreferences = getMyUserPref(context!!, MY_USER_PREF)
            setRecyclerViewElements(tempPreferences?.cus_photos_post)
        }
    }


    private inner class LongOperation : AsyncTask<String, Void, MutableList<Post>>() {

        override fun doInBackground(vararg params: String): MutableList<Post> {
            val client = JumblrClient(
                CONSUMERKEY,
                CONSUMERSECRET
            )

            client.setToken(
                TOKEN,
                TOKENSECRET
            )

            var returnedPosts: MutableList<Post>

            val user = client.user()
            println(user.name)
            val params = HashMap<String, Any>()
            params["type"] = "photo"
            returnedPosts = client.blogPosts(usertumblrBlog + PRE_TUMBLR_URL, params)

            return returnedPosts

        }

        override fun onPostExecute(photoPosts: MutableList<Post>) {

            setRecyclerViewMainElements(photoPosts)
            swipeRefresh?.isRefreshing = false

        }

        override fun onPreExecute() {}
        override fun onProgressUpdate(vararg values: Void) {}
    }

    fun setRecyclerViewMainElements(photoPosts: MutableList<Post>) {

        var cont = 1
        var backupPhotoPost = mutableListOf<CusPost>()
        var photoPostsRV = mutableListOf<CusPost>()

        for (x in photoPosts) {

            val cusPhotoPost = CusPost()
            val photoPost = x as PhotoPost
            cusPhotoPost.description =
                HtmlCompat.fromHtml(photoPost.caption, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
            cusPhotoPost.url =
                photoPost.photos[0].originalSize.url

            cusPhotoPost.redirectUrl = x.shortUrl

            if (NUM_POST_SAVED > cont) {
                backupPhotoPost.add(cusPhotoPost)
            }
            photoPostsRV.add(cusPhotoPost)
            cont += 1
        }

        val tempPreferences = getMyUserPref(context!!, MY_USER_PREF)
        tempPreferences?.cus_photos_post = backupPhotoPost
        setMyUserPref(context!!, tempPreferences, MY_USER_PREF)

        setRecyclerViewElements(photoPostsRV)

    }

    fun setRecyclerViewElements(photoPostsRV: MutableList<CusPost>?){
        my_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = AdapterPhotoPost(photoPostsRV)
        my_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged();
    }


}
