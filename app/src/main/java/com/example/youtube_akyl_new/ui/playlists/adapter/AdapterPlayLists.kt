package com.example.youtube_akyl_new.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube_akyl_new.data.model.PlayListsModel
import com.example.youtube_akyl_new.databinding.ItemPlaylistsBinding

class AdapterPlayLists() : RecyclerView.Adapter<AdapterPlayLists.PlayListsViewHolder>() {

    @SuppressLint("NotifiDataSetChanged")
    fun setList(liste: List<PlayListsModel.Item>){
        this.list = liste as ArrayList<PlayListsModel.Item>
        notifyDataSetChanged()
    }

    private var list = arrayListOf<PlayListsModel.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListsViewHolder {
        return PlayListsViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayListsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class PlayListsViewHolder(private val binding: ItemPlaylistsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PlayListsModel.Item) {
            binding.image.load(item.snippet.thumbnails.default.url)
            binding.tvTitle.text = item.snippet.title
            binding.tvVideo.text = "${item.contentDetails.itemCount} video"
        }

    }
}
