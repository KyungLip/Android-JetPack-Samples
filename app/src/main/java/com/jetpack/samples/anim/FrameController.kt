package com.jetpack.samples.anim

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.jetpack.samples.MyApplication
import com.jetpack.samples.R

object FrameController {
    fun createFrameAnim(): AnimationDrawable? {
        return createAnimationDrawable()
//        return loadDrawable(R.drawable.anim_frame) as? AnimationDrawable
    }

    private fun createAnimationDrawable(): AnimationDrawable {
        val animationDrawable = AnimationDrawable()
        loadDrawable(R.drawable.ic_launcher_background)?.let {
            animationDrawable.addFrame(it, 1000)
        }
        loadDrawable(R.drawable.ic_launcher_foreground)?.let {
            animationDrawable.addFrame(it, 1000)
        }
        animationDrawable.isOneShot = true
        return animationDrawable
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun loadDrawable(@DrawableRes resIdRes: Int): Drawable? {
        return MyApplication.getAppContext().getDrawable(resIdRes)
    }
}