package org.d3if3003.novelstine.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.db.User
import org.d3if3003.novelstine.model.getNama
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<User, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User, newItem: User
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: User, newItem: User
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    class ViewHolder(private val binding: ItemHistoriBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: User) {
            val hasilBmi = item.getNama()
            binding.tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            binding.dataTextView.text = binding.root.context.getString(
                R.string.data_x, item.nama
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}
