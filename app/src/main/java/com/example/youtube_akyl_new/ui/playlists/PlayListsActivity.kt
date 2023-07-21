package com.example.youtube_akyl_new.ui.playlists

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_akyl_new.core.base.BaseActivity
import com.example.youtube_akyl_new.core.ext.ConnectionLiveData
import com.example.youtube_akyl_new.core.network.Resource
import com.example.youtube_akyl_new.databinding.ActivityPlaylistsBinding
import com.example.youtube_akyl_new.ui.playlists.adapter.AdapterPlayLists

class PlayListsActivity :
    BaseActivity<ActivityPlaylistsBinding, PlaylistViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private var adapter = AdapterPlayLists()

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) { isConnection ->
            if (isConnection) {
                binding.noInternet.visibility = View.GONE
                binding.yesInternet.visibility = View.VISIBLE
            } else {
                binding.noInternet.visibility = View.VISIBLE
                binding.yesInternet.visibility = View.GONE
            }
        }
    }

    override fun setupLiveData() {
        super.setupLiveData()

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.playlists().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.let { it1 -> adapter.setList(it1.items) }
                    viewModel.loading.postValue(false)
                }

                Resource.Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }

                Resource.Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
