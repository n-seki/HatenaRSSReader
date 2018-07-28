package seki.com.hatenarssreader.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name="rdf:RDF", strict = false)
data class Result(
        @set:ElementList(inline = true)
        @get:ElementList(inline = true)
        var item: MutableList<RssItem> = mutableListOf()
)