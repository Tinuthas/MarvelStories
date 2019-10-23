package com.waterclock.marvelstories.domain

import com.waterclock.marvelstories.data.model.CharactersModel
import com.waterclock.marvelstories.util.BaseCallback

class StoriesContract {

    interface Repository {
        fun getAllStories(listener : BaseCallback<CharactersModel>)
    }
}