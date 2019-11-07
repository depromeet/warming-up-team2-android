package com.depromeet.android.childcare.util

import androidx.recyclerview.widget.RecyclerView
import com.studyfirstproject.base.BaseViewHolder

class ExpandableRecyclerAdapter {
    private val TYPE_PARENT = 1
    private val TYPE_CHILD = 2

}

interface Parent<C> {

    fun getChilds(): List<C>
}