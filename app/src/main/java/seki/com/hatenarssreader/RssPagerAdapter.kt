package seki.com.hatenarssreader

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class RssPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return RssListFragment.newInstance(position.toCategoryName())
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return position.toPageTitle()
    }

    private fun Int.toCategoryName(): String = when(this) {
        0 -> "all"
        1 -> "social"
        2 -> "economics"
        3 -> "life"
        else -> throw IllegalStateException()
    }

    private fun Int.toPageTitle(): String = when(this) {
        0 -> "総合"
        1 -> "世の中"
        2 -> "政治と経済"
        3 -> "暮らし"
        else -> throw IllegalStateException()
    }
}