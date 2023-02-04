package com.example.celebio.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.celebio.R
import com.example.celebio.data.model.Articles
import com.example.celebio.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class DetailFragment : Fragment() {

    @FragmentScoped
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args : DetailFragmentArgs by navArgs()
    private lateinit var article: Articles

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = args.article
        binding.articles = article
        binding.mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.apply {
            Glide.with(this@DetailFragment)
                .load(article.contentThumbnail)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(ivArticleThumbnail)
        }
    }
}