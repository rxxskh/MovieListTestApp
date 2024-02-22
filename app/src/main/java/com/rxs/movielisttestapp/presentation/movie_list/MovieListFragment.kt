package com.rxs.movielisttestapp.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rxs.movielisttestapp.databinding.FragmentMovieListBinding
import com.rxs.movielisttestapp.presentation.movie_list.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val vm: MovieListViewModel by viewModels()

    private lateinit var movieListAdapter: MovieListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)

        setupView()
        startObserve()

        return binding.root
    }

    private fun setupView() {
        binding.apply {
            rvMovieListFragment.layoutManager = LinearLayoutManager(
                this@MovieListFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            movieListAdapter =
                MovieListAdapter(list = emptyList(), onGenreClick = { vm.selectGenre(it) })
            rvMovieListFragment.adapter = movieListAdapter

            tvMovieListFragmentErrorBtn.setOnClickListener {
                vm.loadData()
            }
        }
    }

    private fun startObserve() {
        vm.listItems.observe(viewLifecycleOwner) {
            movieListAdapter.list = it
            movieListAdapter.notifyDataSetChanged()
        }
        vm.isLoading.observe(viewLifecycleOwner) {
            binding.apply {
                rvMovieListFragment.visibility =
                    if (!it && vm.errorMessage.isEmpty()) View.VISIBLE else View.GONE
                tvMovieListFragmentErrorMessage.text = vm.errorMessage
                clMovieListFragmentError.visibility =
                    if (!it && vm.errorMessage.isNotEmpty()) View.VISIBLE else View.GONE
                pbMovieListFragment.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}