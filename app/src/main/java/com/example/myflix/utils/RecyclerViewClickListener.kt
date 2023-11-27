package com.example.myflix.utils

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewItemClicked(v: View, pos: Int)
}