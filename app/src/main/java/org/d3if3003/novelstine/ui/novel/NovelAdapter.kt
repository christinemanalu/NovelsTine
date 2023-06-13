package org.d3if3003.novelstine.ui.novel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.databinding.ListItemBinding
import org.d3if3003.novelstine.model.Novel

class NovelAdapter : RecyclerView.Adapter<NovelAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(novel: Novel) = with(binding) {
            judul.text = novel.judul
            author.text = novel.author
            genre.text = novel.genre
            tahun.text = novel.tahun.toString()
            imageView.setImageResource(R.drawable.mariposa)
        }
    }

    private val data = mutableListOf<Novel>()
    fun updateData(newData: List<Novel>) { data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
