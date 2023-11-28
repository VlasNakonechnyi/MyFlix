package com.example.myflix.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.SearchedMovie

class DiffUtilCallback(
    private val oldList: List<SearchedMovie>,
    private val newList: List<SearchedMovie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] === newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

}