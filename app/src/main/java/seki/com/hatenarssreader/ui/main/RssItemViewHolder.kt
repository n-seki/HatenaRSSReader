package seki.com.hatenarssreader.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import seki.com.hatenarssreader.databinding.LayoutRssItemBinding

class RssItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binder: LayoutRssItemBinding = LayoutRssItemBinding.bind(view)
}