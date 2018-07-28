package seki.com.hatenarssreader.data

import org.jsoup.Jsoup
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import java.text.SimpleDateFormat
import java.util.*

@Root(name = "item", strict = false)
data class RssItem(
        @set:Element
        @get:Element
        var title: String = "",

        @set:Element
        @get:Element
        var link: String = "",

        @set:Element
        @get:Element
        var description: String = "",

        @Path("dc/date")
        @set:Element
        @get:Element
        var date: String = "",

        @Path("content/encoded")
        @set:Element(name = "encoded")
        @get:Element(name = "encoded")
        var content: String = "",

        @Path("hatena/bookmarkcount")
        @set:Element(name = "bookmarkcount")
        @get:Element(name = "bookmarkcount")
        var bookmarkCount: String = ""
) {
    val imageUrl: String by lazy { findImageUrlFromContent() }

    val displayDate: String by lazy { convertDisplayDate() }

    private fun findImageUrlFromContent(): String {
        val document = Jsoup.parse(content)
        val imgElement = document.getElementsByTag("img")[1]
        return imgElement.attr("src")
    }

    private fun convertDisplayDate(): String {
        val date: Date = FORMAT_TO_DATE.parse(date)
        return FORMAT_TO_STR.format(date)
    }

    companion object {
        private val FORMAT_TO_DATE = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        private val FORMAT_TO_STR = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    }
}