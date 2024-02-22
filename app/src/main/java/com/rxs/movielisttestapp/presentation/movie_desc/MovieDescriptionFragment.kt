package com.rxs.movielisttestapp.presentation.movie_desc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.rxs.movielisttestapp.R
import com.rxs.movielisttestapp.databinding.FragmentMovieDescriptionBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MovieDescriptionFragment : Fragment() {

    private var _binding: FragmentMovieDescriptionBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDescriptionFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDescriptionBinding.inflate(layoutInflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {
        val movie = args.selectedMovieUI
        binding.apply {
            ivMovieDescFragmentBack.setOnClickListener {
                Navigation.createNavigateOnClickListener(
                    MovieDescriptionFragmentDirections.actionMovieDescriptionFragmentToMovieListFragment()
                ).onClick(it)
            }

            tvMovieDescFragmentDescription.text = movie.description
            tvMovieListFragmentName.text = movie.name
            if (movie.imageUrl.isNotEmpty()) {
                Picasso.get().load(movie.imageUrl)
                    .placeholder(R.drawable.im_not_found).into(ivMovieDescFragmentImage)
            }
            tvMovieDescFragmentLocalName.text = movie.localizedName
            tvMovieDescFragmentGenres.text = "${movie.genres.joinToString()}, ${movie.year} год"
            tvMovieDescFragmentRating.text = ((movie.rating * 10.0).roundToInt() / 10.0).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}