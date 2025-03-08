package com.codepath.bestsellerlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.R

class MovieViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }



    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById(R.id.movie_title)
        val mMovieImage: ImageView = mView.findViewById(R.id.movie_image)
        val mMovieDesc: TextView = mView.findViewById(R.id.movie_description)

        override fun toString(): String {
            return "${mMovieTitle.text} '${mMovieDesc.text}'"
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDesc.text = movie.description

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/${movie.imageUrl}")
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            mListener?.onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
