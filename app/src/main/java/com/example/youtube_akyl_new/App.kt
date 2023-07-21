package com.example.youtube_akyl_new

import android.app.Application
import com.example.youtube_akyl_new.repository.Repository

class App : Application() {

    companion object {
        val repository = Repository()
    }
}