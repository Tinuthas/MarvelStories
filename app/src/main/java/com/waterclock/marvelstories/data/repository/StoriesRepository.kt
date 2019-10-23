package com.waterclock.marvelstories.data.repository

import com.waterclock.marvelstories.data.StoriesApi
import com.waterclock.marvelstories.data.model.CharactersModel
import com.waterclock.marvelstories.domain.StoriesContract
import com.waterclock.marvelstories.util.BaseCallback
import com.waterclock.marvelstories.util.PRIVATE_KEY
import com.waterclock.marvelstories.util.PUBLIC_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class StoriesRepository : StoriesContract.Repository{

    override fun getAllStories(listener: BaseCallback<CharactersModel>) {
        val ts = System.currentTimeMillis().toString()
        var hash = getMd5(ts)
        StoriesApi.invoke().getAllStories(ts, PUBLIC_KEY, hash).enqueue(object : Callback<CharactersModel>{
            override fun onFailure(call: Call<CharactersModel>, t: Throwable) {
                t.message?.let { message -> listener.onUnsuccessful(message) }
            }

            override fun onResponse(
                call: Call<CharactersModel>,
                response: Response<CharactersModel>
            ) {
                if(!response.isSuccessful) return listener.onUnsuccessful(response.message())
                if(response.body() == null) return listener.onUnsuccessful("Lista Vazia")
                response.body()?.let { list ->
                    listener.onSuccessful(list)
                }
            }

        })
    }

    private fun getMd5(ts: String): String {
        try {

            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(ts.toByteArray()
                    + PRIVATE_KEY.toByteArray()
                    + PUBLIC_KEY.toByteArray())

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }


}