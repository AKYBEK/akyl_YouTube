package com.example.youtube_akyl_new.ui.playlists

import androidx.lifecycle.LiveData
import com.example.youtube_akyl_new.App
import com.example.youtube_akyl_new.core.base.BaseViewModel
import com.example.youtube_akyl_new.core.network.Resource
import com.example.youtube_akyl_new.data.model.PlayListsModel

class PlaylistViewModel : BaseViewModel() {

    fun playlists(): LiveData<Resource<PlayListsModel>> {
        return App.repository.getPlayLists()
    }

}
