package com.waterclock.marvelstories.util


interface BaseCallback<T> {
    fun onSuccessful(value : T)
    fun onUnsuccessful(error: String)
}