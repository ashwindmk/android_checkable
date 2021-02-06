package com.ashwin.android.checkable

import android.content.Context
import androidx.core.content.ContextCompat

fun Int.resolvedColor(context: Context): Int = ContextCompat.getColor(context, this)
