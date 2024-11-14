package com.foxnks.cryptoapp.ui.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.View

class SparkLineLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var data: List<Double> = emptyList()

    fun setSparkLineData(data: List<Double>) {
        this.data = data
        // Add logic to update the view with the new data
        invalidate() // Redraw the view
    }

    // Add other necessary methods and logic for the SparkLineLayout
}