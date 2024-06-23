package com.example.charigochi.model

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.example.charigochi.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


enum class Track {
    CALM, ANXIOUS, TIRED, FUNKY
}



@Singleton
class MusicProvider @Inject constructor(@ApplicationContext val context : Context) {

    val calm
        get() = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .appendPath(R.raw.calm.toString())
            .build()

    val anxious
        get() = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .appendPath(R.raw.anxious.toString())
            .build()

    val funky
        get() = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .appendPath(R.raw.funky.toString())
            .build()

    val tired
        get() = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .appendPath(R.raw.tired.toString())
            .build()
}