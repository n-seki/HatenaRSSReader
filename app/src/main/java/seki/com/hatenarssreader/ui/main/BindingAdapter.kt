package seki.com.hatenarssreader.ui.main

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl", "bing:errorImage")
fun loadImage(imageView: ImageView, imageUrl: String, errorImage: Drawable) {
    if (imageUrl.isEmpty()) {
        imageView.background = errorImage
        return
    }

    Picasso.get()
            .load(imageUrl)
            .error(errorImage)
            .fit()
            .into(imageView)
}