package com.waterclock.marvelstories.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.waterclock.marvelstories.R
import com.waterclock.marvelstories.data.model.CharactersModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainContract.Presenter

    private var shortAnimTime:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        presenter.getStories()
    }

    @SuppressLint("ShowToast")
    override fun notification(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }

    override fun initView(storyModel: CharactersModel) {
        recyclerViewStories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AdapterStories(storyModel.data.results)
        }
    }

    override fun showProgressRecycler(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
        recyclerViewStories.visibility = if(show) View.INVISIBLE else View.VISIBLE
        progressBar.animate().setDuration(shortAnimTime.toLong()).alpha(if(show) 1F else 0F)
            .setListener( object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBar.visibility = if(show) View.VISIBLE else View.GONE
                }
            })    }

    override fun logout() {
        finish()
    }
}