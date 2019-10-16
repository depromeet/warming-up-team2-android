package com.depromeet.android.childcare.bookdetail

import android.content.Context
import android.widget.Toast

interface BookDetailNavigator {
    fun showOptionDialog(bookDetailId: Int)
    fun backToBookList()
}

class BookDetailNavigatorImpl(
    private val context: Context
): BookDetailNavigator {
    override fun showOptionDialog(bookDetailId: Int) {
        Toast.makeText(context, "option click in bookdetail with id $bookDetailId", Toast.LENGTH_SHORT).show()
    }

    override fun backToBookList() {
        val activity = context as? BookDetailActivity ?: return
        if (!activity.isFinishing) {
            activity.finish()
        }
    }

}