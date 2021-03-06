package com.ashwin.android.checkable.imagebutton

import android.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageButton
import com.ashwin.android.checkable.Constant

class CheckableImageButton(context: Context, attributeSet: AttributeSet) : AppCompatImageButton(
        context,
        attributeSet
), Checkable {
    private var mChecked: Boolean = false

    private val CHECKED_STATE_SET = intArrayOf(R.attr.state_checked)
    private val DISABLED_STATE_OPACITY = .3f

    init {
        imageTintMode = PorterDuff.Mode.SRC_IN

        isFocusable = true
        isClickable = true
        outlineProvider = null
    }

    // Checkable
    override fun setChecked(checked: Boolean) {
        Log.d(Constant.TAG_DEBUG, "setChecked: $checked")
        if (mChecked == checked) {
            return
        }
        mChecked = checked
        refreshDrawableState()
    }

    // Checkable
    override fun isChecked(): Boolean {
        Log.d(Constant.TAG_DEBUG, "isChecked")
        return mChecked
    }

    // Checkable
    override fun toggle() {
        Log.d(Constant.TAG_DEBUG, "toggle")
        setChecked(!mChecked)
    }

    // View
    override fun onCreateDrawableState(extraSpace: Int): IntArray? {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (mChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }

    // View
    override fun refreshDrawableState() {
        super.refreshDrawableState()
        alpha = if (isEnabled) {
            1f
        } else {
            DISABLED_STATE_OPACITY
        }
    }

    // View
    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

    // View
    override fun performClick(): Boolean {
        Log.d(Constant.TAG_DEBUG, "performClick")
        toggle()
        return super.performClick()
    }

    fun setBackgroundColors(colorStateList: ColorStateList?) {
        backgroundTintList = colorStateList
        isDuplicateParentStateEnabled = true
    }

    fun setIconColors(colorStateList: ColorStateList?) {
        imageTintList = colorStateList
        isDuplicateParentStateEnabled = true
    }

     class SavedState : BaseSavedState {
        var count = false

        /**
         * Constructor called from [CounterFab.onSaveInstanceState]
         */
        constructor(superState: Parcelable?) : super(superState) {}

        /**
         * Constructor called from [.CREATOR]
         */
        constructor(`in`: Parcel) : super(`in`) {
            count = `in`.readBoolean()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeBoolean(count)
        }

        override fun toString(): String {
            return (CheckableImageButton::class.java.getSimpleName() + "." + SavedState::class.java.simpleName + "{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " count=" + count + "}")
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState?> = object : Parcelable.Creator<SavedState?> {
                override fun createFromParcel(`in`: Parcel): SavedState? {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss: SavedState = SavedState(superState)
        ss.count = mChecked
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val ss: SavedState = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        mChecked = ss.count
        requestLayout()
    }
}
