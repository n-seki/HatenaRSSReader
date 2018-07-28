package seki.com.hatenarssreader

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) {
        return
    }
    Picasso.get().load(imageUrl).fit().into(imageView)
}