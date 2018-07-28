package seki.com.hatenarssreader.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import seki.com.hatenarssreader.R
import seki.com.hatenarssreader.data.RssItem

class RssListAdapter(private val listener: ItemClickListener): RecyclerView.Adapter<RssItemViewHolder>() {

    var data: List<RssItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onClickRssItem(rssItem: RssItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_rss_item, parent, false)

        return RssItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: RssItemViewHolder, position: Int) {
        val item = data[position]
        viewHolder.binder.rssItem = item
        viewHolder.binder.root.setOnClickListener {
            listener.onClickRssItem(item)
        }
    }
}