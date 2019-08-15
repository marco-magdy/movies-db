package com.movies.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.movies.R
import com.movies.adapter.MoviesAdapter
import com.movies.adapter.SearchResultAdapter
import com.movies.data.entity.Movie
import com.movies.data.entity.Status
import com.movies.di.ViewModelFactory
import com.movies.viewmodel.MoviesViewModel
import com.movies.widget.PaginationScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject


/**
 *Created by marco on 2019-08-09
 */
class MoviesActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {


    lateinit var viewModel: MoviesViewModel
    @field:[Inject]
    lateinit var viewModelFactory: ViewModelFactory<MoviesViewModel>
    @field:[Inject]
    lateinit var mAdapter: MoviesAdapter
    private lateinit var searchAutoComplete: SearchView.SearchAutoComplete

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        setSupportActionBar(toolbar)
        AndroidInjection.inject(this)
        initViewModel()
        initObservables()
        initViews()
        viewModel.movies()
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this)
        with(recyclerView) {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    if (viewModel.pageIndex > 1) viewModel.movies()
                }
            })
        }
        swipeRefreshLayout.setOnRefreshListener(this)
        ivLogo.setOnClickListener { recyclerView.smoothScrollToPosition(0) }
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun initObservables() {
        viewModel.moviesLiveData.observe(this,
                Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            if (viewModel.pageIndex == 1) mAdapter.addLoading()
                        }
                        Status.SUCCESS -> {
                            if (viewModel.pageIndex == 1) mAdapter.removeLoading()
                            swipeRefreshLayout.isRefreshing = false
                            it?.data?.let { items -> mAdapter.addAll(items) }
                        }
                        else -> {
                            progressBar.visibility = View.GONE
                            showMessage(it.message!!)
                        }
                    }
                })

        viewModel.searchResultLiveData.observe(this,
                Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            it?.data?.let { items -> setSearchResult(items) }
                        }
                        else -> {
                        }
                    }
                })
    }

    private fun setSearchResult(movies: ArrayList<Movie>) {
        val searchResultsAdapter = SearchResultAdapter(this, movies)
        searchAutoComplete.setAdapter(searchResultsAdapter)
    }

    override fun onRefresh() {
        mAdapter.clear()
        viewModel.pageIndex = 1
        viewModel.movies()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchWithAutoComplete(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun searchWithAutoComplete(searchView: SearchView) {
        searchAutoComplete = searchView.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.setTextColor(Color.WHITE)
        searchAutoComplete.setDropDownBackgroundResource(R.color.colorPrimary)

        viewModel.search(searchView)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MoviesActivity::class.java)
            activity.startActivity(intent)
        }
    }

}