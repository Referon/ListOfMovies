package ru.referon.listofmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.referon.listofmovies.databinding.CardLayoutBinding
import ru.referon.listofmovies.model.Result


class Adapter() : androidx.recyclerview.widget.ListAdapter<Result, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {

        binding.apply {
            name.text = result.display_title
            description.text = result.summary_short
            Glide.with(itemView)
                .load(result.multimedia.src)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .apply(RequestOptions().override(700, 700))
                .into(image)

        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}