package seki.com.hatenarssreader

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_rss_list.*
import javax.inject.Inject

class RssListFragment: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: RssListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RssListViewModel::class.java) }

    private lateinit var listener: RssListAdapter.ItemClickListener

    companion object {
        private const val CATEGORY = "category"

        fun newInstance(category: String): Fragment {
            return RssListFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY, category)
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainActivity) {
            context.getAppComponent().inject(this)
        }

        if (context is RssListAdapter.ItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rss_list, container, false)
        initView(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun initView(view: View) {
        val rssListView = view.findViewById<RecyclerView>(R.id.rss_listView)
        rssListView.adapter = RssListAdapter(listener)

        rssListView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorAccent))
        swipeRefresh.setOnRefreshListener {
            refreshList(true)
        }

        viewModel.rssListData.observe(this, Observer { rssListData ->
            rssListData?.let {
                val adapter = rssListView.adapter as RssListAdapter
                adapter.data = it
            }
            swipe_refresh.isRefreshing = false
        })
    }

    private fun refreshList(forceFetch: Boolean = false) {
        arguments?.getString(CATEGORY)?.let {
            viewModel.init(it, forceFetch)
            swipe_refresh.isRefreshing = true
        }
    }
}