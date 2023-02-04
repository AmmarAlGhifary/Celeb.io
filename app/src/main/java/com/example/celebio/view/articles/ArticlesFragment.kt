package com.example.celebio.view.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.celebio.databinding.FragmentArticlesBinding
import com.example.celebio.view.articles.adapter.ArticleAdapter
import com.example.celebio.view.articles.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@FragmentScoped
@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    @FragmentScoped
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter()
        binding.mainToolbar.navigationIcon = null
        binding.rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
            viewModel.getArticles()
            setHasFixedSize(true)
        }
        viewModel.articlesResponse.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                articleAdapter.dataArticles = result
                binding.articles
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.srlArticles.setOnRefreshListener {
            viewModel.getArticles()
            binding.srlArticles.isRefreshing = false
        }
    }
}


