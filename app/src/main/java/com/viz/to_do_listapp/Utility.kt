package com.viz.to_do_listapp

import androidx.compose.animation.core.Easing

fun Easing.transform(from: Float, to: Float, value: Float): Float {
    return transform(((value - from) * (1f / (to - from))).coerceIn(0f, 1f))
}