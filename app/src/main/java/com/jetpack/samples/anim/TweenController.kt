package com.jetpack.samples.anim

import android.util.Log
import android.view.animation.*
import com.jetpack.samples.MyApplication
import com.jetpack.samples.R

object TweenController {
    private const val TAG="kylp"
    private const val TYPE_CODE = 0
    private const val TYPE_XML = 1
    
    /**
     * [LinearInterpolator] 线性差值器
     * [AccelerateInterpolator] 加速差值器
     * [DecelerateInterpolator] 减速差值器
     * [AccelerateDecelerateInterpolator] 先加速后减速差值器
     * [CycleInterpolator] 循环加速差值器
     * [AnticipateInterpolator] 开始向后然后向前差值器
     * [AnticipateOvershootInterpolator] 开始向后然后向前冲出目标值，最后又回到目标值
     * [OvershootInterpolator] 开始向前然后冲出目标值，最后又回到目标值
     */
    fun createRotateAnim(): Animation {
        if (randomAnimType() == TYPE_XML) {
            Log.d(TAG, "createRotateAnim xml")
            return AnimationUtils.loadAnimation(MyApplication.getAppContext(), R.anim.anim_rotate)
        }
        Log.d(TAG, "createRotateAnim code")
        val rotateAnim = RotateAnimation(
            0f,
            360f,
            RotateAnimation.ABSOLUTE,
            0.5f,
            RotateAnimation.ABSOLUTE,
            0.5f
        )
        rotateAnim.duration = 5000L //动画持续时间 ms
        rotateAnim.fillBefore = false //动画结束时停留在第一帧
        rotateAnim.fillAfter = true//动画结束时停留在最后一帧
//        rotateAnim.repeatCount = RotateAnimation.INFINITE//动画重复次数，INFINITE为无限次
//        rotateAnim.repeatMode = RotateAnimation.RESTART //RESTART:重新开始，REVERSE:翻着回去
        rotateAnim.interpolator = LinearInterpolator()//动画差值器
        rotateAnim.setAnimationListener(MyAnimListener())
        return rotateAnim
    }

    fun createTranslateAnim(): Animation {
        if (randomAnimType() == TYPE_XML) {
            Log.d(TAG, "createTranslateAnim xml")
            return AnimationUtils.loadAnimation(
                MyApplication.getAppContext(),
                R.anim.anim_translate
            )
        }
        Log.d(TAG, "createRotateAnim code")
        val translateAnim = TranslateAnimation(0f, 200f, 0f, 0f)
        translateAnim.duration = 2000L //动画持续时间 ms
        translateAnim.fillBefore = false //动画结束时停留在第一帧
        translateAnim.fillAfter = true//动画结束时停留在最后一帧
//        rotateAnim.repeatCount = RotateAnimation.INFINITE//动画重复次数，INFINITE为无限次
//        rotateAnim.repeatMode = RotateAnimation.RESTART //RESTART:重新开始，REVERSE:翻着回去
        translateAnim.interpolator = CycleInterpolator(5f)//动画差值器
        translateAnim.setAnimationListener(MyAnimListener())
        return translateAnim
    }

    fun createAlphaAnim(): Animation {
        if (randomAnimType() == TYPE_XML) {
            Log.d(TAG, "createAlphaAnim xml")
            return AnimationUtils.loadAnimation(MyApplication.getAppContext(), R.anim.anim_alpha)
        }
        Log.d(TAG, "createAlphaAnim code")
        val alphaAnim = AlphaAnimation(0.0f, 1.0f)
        alphaAnim.duration = 2000
        alphaAnim.interpolator = AccelerateDecelerateInterpolator()
        alphaAnim.setAnimationListener(MyAnimListener())
        return alphaAnim
    }

    fun createScaleAnim(): Animation {
        if (randomAnimType() == TYPE_XML) {
            Log.d(TAG, "createScaleAnim xml")
            return AnimationUtils.loadAnimation(MyApplication.getAppContext(), R.anim.anim_scale)
        }
        Log.d(TAG, "createScaleAnim code")
        val scaleAnim = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnim.duration = 2000
        scaleAnim.interpolator = LinearInterpolator()
        scaleAnim.setAnimationListener(MyAnimListener())
        return scaleAnim
    }

    private fun randomAnimType(): Int {
//        return Random().nextInt(2)
        return TYPE_CODE
    }

    class MyAnimListener : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            Log.d(TAG, "动画开始")
        }

        override fun onAnimationEnd(animation: Animation?) {
            Log.d(TAG, "动画结束")
        }

        override fun onAnimationRepeat(animation: Animation?) {
            Log.d(TAG, "动画重复")
        }

    }

    class MyInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float {
            return input * 1.1f
        }

    }
}