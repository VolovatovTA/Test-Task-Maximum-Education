package com.example.testtaskmaximumeducation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskmaximumeducation.dagger.MyApplication
import com.example.testtaskmaximumeducation.databinding.FragmentArticleListBinding
import javax.inject.Inject

class ListArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleListBinding
    private val columnCountInGrid = 2

    lateinit var viewModelArticleList: ViewModelArticleList
    @Inject
    lateinit var myAdapter: MyItemRecyclerViewAdapter
    @Inject
    lateinit var repository: Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        viewModelArticleList = ViewModelProvider(
            requireActivity(),
            MyViewModelFactory(repository)
        )[ViewModelArticleList::class.java]
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val itemRefresh = menu.findItem(R.id.app_bar_show_grid)
        itemRefresh.setIcon(
            if (viewModelArticleList.isGrid) {
                R.drawable.ic_baseline_list_24
            } else {
                R.drawable.ic_baseline_grid_24
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_refresh_data -> refreshData()
            R.id.app_bar_show_grid -> {
                viewModelArticleList.isGrid = !viewModelArticleList.isGrid
                changeLayoutManager()

                item.setIcon(
                    if (viewModelArticleList.isGrid) {
                        R.drawable.ic_baseline_list_24
                    } else {
                        R.drawable.ic_baseline_grid_24
                    }
                )
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeLayoutManager() {

        viewModelArticleList.columnCount = if (viewModelArticleList.isGrid) {
            columnCountInGrid
        } else {
            1
        }
        setTypeOfRecyclerView()
    }

    private fun refreshData() {
        viewModelArticleList.refreshArticlesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (viewModelArticleList.repository.listArticle.isEmpty()) {
            viewModelArticleList.loadArticlesList()
        }

        binding = FragmentArticleListBinding.inflate(layoutInflater, container, false)

        myAdapter.setOnItemClickListener { position ->
            val detailsFragment = DetailsArticleFragment.newInstance(position)
            val transactionFragments =
                requireActivity().supportFragmentManager.beginTransaction()

            transactionFragments.replace(R.id.fragmentContainerView, detailsFragment)
            transactionFragments.addToBackStack("list in previous state")
            transactionFragments.commit()
        }

        setTypeOfRecyclerView()

        viewModelArticleList.listArticleFormState.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it) {
                    ViewModelArticleList.ListArticleFormState.ALL_IS_GOOD -> setRecyclerView()
                    ViewModelArticleList.ListArticleFormState.NETWORK_ERROR -> showNetworkError()
                    ViewModelArticleList.ListArticleFormState.DATA_LOADING -> showProgressLoading()

                }
            } else return@Observer

        })

        return binding.root
    }

    private fun setTypeOfRecyclerView() {
        myAdapter.isGrid = viewModelArticleList.isGrid
        with(binding.list) {
            layoutManager = when {
                viewModelArticleList.columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, viewModelArticleList.columnCount)
            }
            adapter = myAdapter
        }
    }

    private fun showProgressLoading() {
        binding.list.visibility = View.GONE
    }

    private fun showNetworkError() {
        Toast.makeText(requireContext(), "Sorry, the connection is lost", Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerView() {
        binding.list.visibility = View.VISIBLE

    }

}