package com.example.testtaskmaximumeducation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.testtaskmaximumeducation.dagger.MyApplication
import com.example.testtaskmaximumeducation.databinding.FragmentDetailsArticleBinding
import javax.inject.Inject
import kotlin.properties.Delegates

private const val ARG_POSITION = "position"

class DetailsArticleFragment : Fragment() {
    private var position by Delegates.notNull<Int>()
    private lateinit var binding: FragmentDetailsArticleBinding
    @Inject lateinit var viewModel: ViewModelArticleList

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsArticleBinding.inflate(layoutInflater, container, false)
        binding.imgB.setOnClickListener { requireActivity().onBackPressed() }

        val article =  viewModel.repository.listArticle[position]
        with(binding){
            SourceInDetails.text = article.newsSite
            TitleInDetails.text = article.title
            TextOfArticleInDetails.text = article.summary
        }
        Glide.with(requireContext())
            .load(article.imageUrl)
            .error(R.drawable.ic_baseline_photo_24)
            .into(binding.imageViewInDetails)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(position: Int) =
            DetailsArticleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}