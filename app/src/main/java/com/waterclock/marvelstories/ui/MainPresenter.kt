package com.waterclock.marvelstories.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.waterclock.marvelstories.data.model.CharactersModel
import com.waterclock.marvelstories.data.repository.StoriesRepository
import com.waterclock.marvelstories.util.BaseCallback
import com.waterclock.marvelstories.util.CONNECTION_INTERNTET_ERROR
import java.lang.Exception

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {


    override fun getStories() {
        try {
            StoriesRepository().getAllStories(object: BaseCallback<CharactersModel>{
                override fun onSuccessful(value: CharactersModel) {
                    view.initView(value)
                }

                override fun onUnsuccessful(error: String) {
                    view.notification(error)
                }

            })
        }catch (e: Exception){
            e.message?.let { view.notification(it) }
            view.logout()
        }

    }

    override fun validateNetwork(activity: AppCompatActivity): Boolean {
        return try {
            verifyNetwork(activity)
            false
        }catch (e: Exception) {
            view.notification(CONNECTION_INTERNTET_ERROR)
            view.logout()
            true
        }
    }

    private fun verifyNetwork(activity:AppCompatActivity){
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) throw Exception()

    }
}