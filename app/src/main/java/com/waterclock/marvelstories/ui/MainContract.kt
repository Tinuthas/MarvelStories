package com.waterclock.marvelstories.ui

import androidx.appcompat.app.AppCompatActivity
import com.waterclock.marvelstories.data.model.CharactersModel

class MainContract {

    interface View {

        fun notification(message: String)

        fun initView(storyModel: CharactersModel)

        fun showProgressRecycler(show: Boolean)

        fun logout()

    }

    interface Presenter {

        fun getStories()

        fun validateNetwork(activity: AppCompatActivity):Boolean
    }
}