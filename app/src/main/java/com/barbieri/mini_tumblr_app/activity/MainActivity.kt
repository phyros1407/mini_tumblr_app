package com.barbieri.mini_tumblr_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.barbieri.mini_tumblr_app.R
import com.barbieri.mini_tumblr_app.adapters.TabAdapter
import com.barbieri.mini_tumblr_app.fragments.PhotosFragment
import com.barbieri.mini_tumblr_app.fragments.TextFragment
import com.barbieri.mini_tumblr_app.fragments.VideosFragment
import com.barbieri.mini_tumblr_app.models.Preferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*


class MainActivity : AppCompatActivity() {

    var usertumblrBlog = "phyros1407"
    var preferences = Preferences()
    var internetStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_title.text = usertumblrBlog.toUpperCase()

        var moduleProfile:MutableList<String> = mutableListOf()

        moduleProfile.add("Texts")
        moduleProfile.add("Photos")
        moduleProfile.add("Videos")

        setupViewPager(vp_main_menu_tab)
        main_menu_tab.setupWithViewPager(vp_main_menu_tab)
        tabTitle(moduleProfile)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = TabAdapter(supportFragmentManager)

        adapter.addFragment(TextFragment())
        adapter.addFragment(PhotosFragment())
        adapter.addFragment(VideosFragment())
        viewPager.adapter = adapter
    }

    private fun tabTitle(modules: MutableList<String>) {
        for (i in 0 until main_menu_tab.tabCount) {
            main_menu_tab.getTabAt(i)?.text = modules[i]
        }
    }


}
