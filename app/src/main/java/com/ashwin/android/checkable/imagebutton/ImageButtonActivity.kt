package com.ashwin.android.checkable.imagebutton

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ashwin.android.checkable.Constant
import com.ashwin.android.checkable.R
import com.ashwin.android.checkable.resolvedColor

internal data class BackgroundStates(
    val tintOn: Int,
    val tintOff: Int,
    val bgColorOn: Int,
    val bgColorOff: Int
)

class ImageButtonActivity : AppCompatActivity() {
    private lateinit var backgroundStatesData: BackgroundStates

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagebutton)
        init()
        setup()
    }

    fun init() {
        val green = R.color.lizard.resolvedColor(this)
        val white = R.color.white.resolvedColor(this)
        val gray = R.color.white_25.resolvedColor(this)
        backgroundStatesData = BackgroundStates(white, green, green, white)
    }

    fun setup() {
        val backgroundColorList = getButtonBackgroundColorSelector()
        val iconColorList = getButtonIconColorSelector()

        val checkableImageButton = findViewById<CheckableImageButton>(R.id.checkable_imagebutton)
        checkableImageButton.setBackgroundColors(backgroundColorList)
        checkableImageButton.setIconColors(iconColorList)

        checkableImageButton.setOnClickListener {
            Log.d(Constant.TAG_DEBUG, "onClick checkableImageButton")
        }
    }

    private fun getButtonBackgroundColorSelector(): ColorStateList? {
        val states = arrayOf(
            intArrayOf(
                android.R.attr.state_enabled,
                android.R.attr.state_checked
            ), // on/clicked state
            intArrayOf(android.R.attr.state_enabled), // off/default state
            intArrayOf(-android.R.attr.state_enabled)  // disabled state
        )

        val colors = intArrayOf(
            backgroundStatesData.bgColorOn,
            backgroundStatesData.bgColorOff,
            R.color.white_transparent.resolvedColor(this)
        )

        return ColorStateList(states, colors)
    }

    private fun getButtonIconColorSelector(): ColorStateList? {
        val states = arrayOf(
            intArrayOf(
                android.R.attr.state_enabled,
                android.R.attr.state_checked
            ), // on/clicked state
            intArrayOf(android.R.attr.state_enabled) // off/default state
        )

        val colors = intArrayOf(
            backgroundStatesData.tintOn,
            backgroundStatesData.tintOff
        )

        return ColorStateList(states, colors)
    }
}
