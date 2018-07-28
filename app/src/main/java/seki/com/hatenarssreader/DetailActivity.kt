package seki.com.hatenarssreader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {

    companion object {
        const val URL = "url"

        fun newIntent(context: Context, url: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        initActionBar()
        showPage()
    }

    private fun initActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item ?: return super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPage() {
        val url = intent.getStringExtra(URL)
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(url)
    }
}