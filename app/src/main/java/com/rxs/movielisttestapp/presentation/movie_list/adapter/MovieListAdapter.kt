package com.rxs.movielisttestapp.presentation.movie_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rxs.movielisttestapp.R
import com.rxs.movielisttestapp.databinding.ItemGenreBinding
import com.rxs.movielisttestapp.databinding.ItemMovieRowBinding
import com.rxs.movielisttestapp.databinding.ItemTitleBinding
import com.rxs.movielisttestapp.presentation.model.MovieUI
import com.rxs.movielisttestapp.presentation.model.toMovieUI
import com.rxs.movielisttestapp.presentation.movie_list.MovieListFragmentDirections
import com.squareup.picasso.Picasso

class MovieListAdapter(
    var list: List<ListItem>,
    var onGenreClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class TitleViewHolder(val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val titleItem = list[position] as ListItem.TitleItem
            binding.apply {
                tvTitleItemName.text = titleItem.name
                val param = clTitleItem.layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(
                    titleItem.margins[0],
                    titleItem.margins[1],
                    titleItem.margins[2],
                    titleItem.margins[3]
                )
                clTitleItem.layoutParams = param
            }
        }
    }

    private inner class GenreViewHolder(val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val genreItem = list[position] as ListItem.GenreItem
            binding.apply {
                clGenreItem.setBackgroundResource(if (genreItem.selected) R.color.light_blue else R.color.white)
                tvGenreItemName.text = genreItem.name
                clGenreItem.setOnClickListener { onGenreClick(genreItem.name) }
            }
        }
    }

    private inner class MovieViewHolder(val binding: ItemMovieRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val movies = (list[position] as ListItem.MovieRowItem).movies
            binding.apply {
                tvMovieRowItemMovie1Name.text = movies[0].localizedName
                if (movies[0].imageUrl.isNotEmpty()) {
                    Picasso.get().load(movies[0].imageUrl)
                        .placeholder(R.drawable.im_not_found).into(ivMovieRowItemMovie1)
                }
                clMovieRowItemMovie1.setOnClickListener {
                    Navigation.createNavigateOnClickListener(
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDescriptionFragment(
                            selectedMovieUI = movies[0].toMovieUI()
                        )
                    ).onClick(it)
                }

                if (movies.size > 1) {
                    tvMovieRowItemMovie2Name.text = movies[1].localizedName
                    if (movies[1].imageUrl.isNotEmpty()) {
                        Picasso.get().load(movies[1].imageUrl)
                            .placeholder(R.drawable.im_not_found).into(ivMovieRowItemMovie2)
                    }
                    clMovieRowItemMovie2.setOnClickListener {
                        Navigation.createNavigateOnClickListener(
                            MovieListFragmentDirections.actionMovieListFragmentToMovieDescriptionFragment(
                                selectedMovieUI = movies[1].toMovieUI()
                            )
                        ).onClick(it)
                    }
                }
                clMovieRowItemMovie2.visibility = if (movies.size > 1) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TITLE_VIEW -> TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ViewType.GENRE_VIEW -> GenreViewHolder(
                ItemGenreBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> MovieViewHolder(
                ItemMovieRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (list[position].viewType) {
            ViewType.TITLE_VIEW -> (holder as TitleViewHolder).bind(position)
            ViewType.GENRE_VIEW -> (holder as GenreViewHolder).bind(position)
            ViewType.MOVIE_VIEW -> (holder as MovieViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}