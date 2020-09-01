package com.example.githubusersearch.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso



fun loadImage(img_url: String, imageView: ImageView) {
    Picasso.get().load(img_url).into(imageView)
}

fun Context.createToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Application.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}