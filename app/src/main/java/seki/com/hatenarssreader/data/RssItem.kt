package seki.com.hatenarssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name="item", strict = false)
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
        var date: String = ""

//        @Path("hatena/bookmarkcount")
//        @set:Element
//        @get:Element
       //var bookMarkCount: String = ""
)