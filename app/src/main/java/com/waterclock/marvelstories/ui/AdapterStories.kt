package com.waterclock.marvelstories.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waterclock.marvelstories.R
import com.waterclock.marvelstories.data.model.Result
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class AdapterStories(private var stories: List<Result>): RecyclerView.Adapter<AdapterStories.StoryViewAdapter>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewAdapter {
        val inflater = LayoutInflater.from(parent.context)
        return StoryViewAdapter(inflater, parent)
    }

    override fun getItemCount(): Int = stories.size

    override fun onBindViewHolder(holder: StoryViewAdapter, position: Int) {
        var story: Result = stories[position]
        holder.bind(story)
    }


    class StoryViewAdapter(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.card_view_stories, parent, false)){
        private var textViewDescription: TextView? = null
        private var imageViewStory: ImageView? = null
        private var textViewName: TextView? = null

        init {
            textViewDescription = itemView.findViewById(com.waterclock.marvelstories.R.id.textViewDescription)
            imageViewStory = itemView.findViewById(com.waterclock.marvelstories.R.id.imageViewStory)
            textViewName = itemView.findViewById(R.id.textViewName)
        }

        @SuppressLint("SetTextI18n")
        fun bind(result: Result){
            textViewName?.text = result.name
            textViewDescription?.text = result.description
            Picasso.get().load(result.thumbnail.path+ "." +result.thumbnail.extension).into(imageViewStory)
        }


    }
}