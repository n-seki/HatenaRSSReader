package seki.com.hatenarssreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import seki.com.hatenarssreader.data.RssItem
import seki.com.hatenarssreader.di.ApplicationComponent

class MainActivity : AppCompatActivity(), RssListAdapter.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initView()
    }

    private fun initView() {
        val pagerAdapter = RssPagerAdapter(supportFragmentManager, this)
        view_pager.adapter = pagerAdapter
        pager_tab.setupWithViewPager(view_pager)
    }

    fun getAppComponent(): ApplicationComponent {
        return (application as App).appComponent
    }

    override fun onClickRssItem(rssItem: RssItem) {
        val intent = DetailActivity.newIntent(this, rssItem.link)
        startActivity(intent)
    }
}
