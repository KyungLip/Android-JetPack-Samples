package com.jetpack.samples.anim

import android.animation.*
import android.util.Log
import android.view.Choreographer
import android.view.View
import android.view.animation.*

object PropertyController {
    private const val TAG = "kylp"

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

    //<***************************************属性动画的旋转，缩放，平移，透明***************************************>

    /**
     * 旋转动画
     */
    fun createRotateAnimator(view: View): Animator {
        val rotateAnim = ObjectAnimator.ofFloat(view, View.ROTATION, 0f, 360f)//正数：顺时针，负数：逆时针
        rotateAnim.duration = 5000L //动画持续时间 ms
//        rotateAnim.repeatCount = RotateAnimation.INFINITE//动画重复次数，INFINITE为无限次
//        rotateAnim.repeatMode = ValueAnimator.RESTART //RESTART:重新开始，REVERSE:翻着回去
        rotateAnim.interpolator = LinearInterpolator()//动画差值器
        rotateAnim.addUpdateListener(MyAnimatorUpdateListener())
        rotateAnim.addListener(MyAnimatorListener())
        return rotateAnim
    }

    /**
     * 平移动画
     */
    fun createTranslateAnimator(view: View): Animator {
        val translateAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0f, 200f, 0f)
        translateAnim.duration = 2000L //动画持续时间 ms
//        translateAnim.repeatCount = RotateAnimation.INFINITE//动画重复次数，INFINITE为无限次
//        translateAnim.repeatMode = ValueAnimator.RESTART //RESTART:重新开始，REVERSE:翻着回去
        translateAnim.interpolator = AccelerateDecelerateInterpolator()//动画差值器
        translateAnim.addUpdateListener(MyAnimatorUpdateListener())
        translateAnim.addListener(MyAnimatorListener())
        translateAnim.setEvaluator(MyEvaluator())
        return translateAnim
    }

    /**
     * 透明动画
     */
    fun createAlphaAnimator(view: View): Animator {
        val alphaAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f)
        alphaAnim.duration = 2000
        alphaAnim.addUpdateListener(MyAnimatorUpdateListener())
        alphaAnim.addListener(MyAnimatorListener())
        return alphaAnim
    }

    /**
     * 缩放动画
     */
    fun createScaleAnimator(view: View): Animator {
        //多属性
        //方式一
        val scaleAnimX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0f, 1f)
        val scaleAnimY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet()
        animatorSet.duration = 2000
        animatorSet.duration = 2000
        animatorSet.interpolator = LinearInterpolator()
        animatorSet.playTogether(scaleAnimX, scaleAnimY)
        animatorSet.addListener(MyAnimatorListener())

        //方式二
        val propertyValuesHolderScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f)
        val propertyValuesHolderScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
        val anim = ObjectAnimator.ofPropertyValuesHolder(
            view,
            propertyValuesHolderScaleX,
            propertyValuesHolderScaleY
        )
        anim.duration = 2000
        anim.interpolator = LinearInterpolator()
        anim.addListener(MyAnimatorListener())
        anim.addUpdateListener(MyAnimatorUpdateListener())
        val valueAnim = ValueAnimator.ofFloat(0f, 1f)
        valueAnim.start()
        return anim
    }

    //<***************************************ViewPropertyAnimator通过View.animate()方式，***************************************>

    fun createViewPropertyAnimator(view: View) {
        view.animate()
            .setDuration(1000)
            .setInterpolator(LinearInterpolator())
            .scaleX(2f)
            .scaleY(2f)
            .start()
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                TODO("Not yet implemented")
            }

        })
    }


}

class MyAnimExperiment {
    var value: Int = 0
        set(value) {
            field = value
            Log.d("kylp", "setValue")
        }
        get() {
            Log.d("kylp", "getValue")

            return field
        }

}

class MyAnimatorUpdateListener : ValueAnimator.AnimatorUpdateListener {
    private val TAG = "kylp"
    override fun onAnimationUpdate(animation: ValueAnimator) {
        Log.d(TAG, "动画更新中:${animation?.animatedValue}")
    }
}

class MyAnimatorListener : Animator.AnimatorListener {
    private val TAG = "kylp"
    override fun onAnimationStart(animation: Animator) {
        Log.d(TAG, "动画开始")
    }

    override fun onAnimationEnd(animation: Animator) {
        Log.d(TAG, "动画结束")
    }

    override fun onAnimationCancel(animation: Animator) {
        Log.d(TAG, "动画取消")
    }

    override fun onAnimationRepeat(animation: Animator) {
        Log.d(TAG, "动画重复")
    }

}

/**
 * 动画监听器适配器模式
 */
class MyAnimatorListenerAdapter : AnimatorListenerAdapter() {
    override fun onAnimationStart(animation: Animator) {
        super.onAnimationStart(animation)
    }
}

class MyInterpolator : Interpolator {
    override fun getInterpolation(input: Float): Float {
        return input * 1.1f
    }
}

class MyEvaluator : TypeEvaluator<Float> {
    override fun evaluate(fraction: Float, startValue: Float, endValue: Float): Float {
        val dF = endValue - startValue
        return startValue + dF * fraction
    }
}
