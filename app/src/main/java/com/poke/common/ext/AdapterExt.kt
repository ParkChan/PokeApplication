package com.poke.common.ext

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poke.R
import com.poke.common.adapter.BaseRecyclerAdapter

@BindingAdapter("replaceItems")
fun replaceItems(recyclerView: RecyclerView, item: List<Any>?) {
    if (item == null) return

    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as BaseRecyclerAdapter<Any>).run {
        replaceItems(item)
    }
}

@BindingAdapter("setPokemonThumb")
fun setPokemonThumb(imageView: ImageView, uri: String?) {
    if (uri.isNullOrBlank()) return

    Glide.with(imageView)
        .load(uri)
        .error(R.drawable.ic_pokemon_default)
        .into(imageView)
}

@BindingAdapter("format", "text")
fun setResourceAndText(textView: TextView, format: String?, text: String?) {
    if (format.isNullOrEmpty() || text.isNullOrEmpty()) return

    textView.text = String.format(format, text)
}
