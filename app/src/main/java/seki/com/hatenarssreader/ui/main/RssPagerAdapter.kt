package seki.com.hatenarssreader.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import seki.com.hatenarssreader.R

class RssPagerAdapter(fragmentManager: FragmentManager, context: Context)
    : FragmentPagerAdapter(fragmentManager) {

    private val categoryAndTitle: Map<Int, Pair<String, String>> =
            mapOf(
                    0 to ("all" to context.getString(R.string.all)),
                    1 to ("social" to context.getString(R.string.social)),
                    2 to ("economics" to context.getString(R.string.economics)),
                    3 to ("life" to context.getString(R.string.life))
            )

    override fun getItem(position: Int): Fragment {
        return RssListFragment.newInstance(categoryAndTitle[position]!!.first)
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryAndTitle[position]!!.second
    }

}