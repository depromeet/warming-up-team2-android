package com.depromeet.android.childcare.book.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.depromeet.android.childcare.R

@BindingAdapter("category_state_color")
fun setBookItemColor(textView: TextView, content: String) {
    textView.setTextColor(
        if (content.isNotEmpty()) {
            R.color.colorPrimary
        } else {
            Color.BLACK
        }
    )
}

@BindingAdapter("view_state_color")
fun setBookItemColor(view: View, content: String) {
    view.setBackgroundColor(
        if (content.isNotEmpty()) {
            R.color.colorPrimary
        } else {
            Color.BLACK
        }
    )
}

@BindingAdapter("main_text", "sequence")
fun setBoldString(textView: TextView, mainText: String, sequence: String) {
    val result = SpannableStringBuilder(mainText)
    val startIndex = mainText.indexOf(sequence)
    result.setSpan(
        StyleSpan(Typeface.BOLD),
        startIndex,
        startIndex + sequence.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    textView.text = result
}

@BindingAdapter("card_view_state_color", "selected_month")
fun setCardViewBackground(cardView: CardView, currentPosition: Int, selectedMonth: Int) {
    cardView.apply {
        setCardBackgroundColor(
            if (currentPosition == selectedMonth) {
                R.color.colorPrimary
            } else {
                R.color.colorGray
            }
        )
    }
}