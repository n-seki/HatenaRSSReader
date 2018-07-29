package seki.com.hatenarssreader.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.oss.licenses.OssLicensesActivity
import kotlinx.android.synthetic.main.activity_main.*
import seki.com.hatenarssreader.App
import seki.com.hatenarssreader.ui.detail.DetailActivity
import seki.com.hatenarssreader.R
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when(item?.itemId) {
                R.id.action_license -> {
                    showLicense()
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }

    private fun showLicense() {
        val intent = Intent(this, OssLicensesActivity::class.java)
        startActivity(intent)
    }

    fun getAppComponent(): ApplicationComponent {
        return (application as App).appComponent
    }

    override fun onClickRssItem(rssItem: RssItem) {
        val intent = DetailActivity.newIntent(this, rssItem.link)
        startActivity(intent)
    }
}
