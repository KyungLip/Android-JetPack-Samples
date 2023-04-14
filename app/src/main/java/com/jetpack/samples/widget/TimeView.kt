package com.jetpack.samples.widget

import android.animation.ValueAnimator
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class TimeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) :
    AppCompatTextView(
        context,
        attrs,
        defStyleAttr
    ) {
    private val DEFAULT_MIN_TIME = 0
    private val DEFAULT_MAX_TIME = 10
    private var mMaxTime = DEFAULT_MAX_TIME
    private val mTimerAnim: ValueAnimator = ValueAnimator.ofInt(DEFAULT_MIN_TIME, DEFAULT_MAX_TIME)
    private var mCountDownTimer:CountDownTimer?=null
    fun startTimer(time: Int) {
        if (mTimerAnim.isRunning) {
            mTimerAnim.end()
        }
        mMaxTime = time
        text = time.toString()
//        mTimerAnim.setIntValues(0, mMaxTime)
//        mTimerAnim.duration=time*1000L
//        mTimerAnim.interpolator=LinearInterpolator()
//        mTimerAnim.addUpdateListener(mTimerAnimUpdateListener)
//        mTimerAnim.start()
        mCountDownTimer?.cancel()
        mCountDownTimer=object :CountDownTimer(time*1000L,1000L){
            override fun onTick(millisUntilFinished: Long) {
                text=(millisUntilFinished/1000L).toString()
            }

            override fun onFinish() {
            }
        }
        mCountDownTimer?.start()
    }

    fun pauseTimer() {
        mTimerAnim.pause()
    }

    fun resumeTimer() {
        mTimerAnim.resume()
    }

    fun stopTimer() {
        mTimerAnim.end()
    }

    private val mTimerAnimUpdateListener =
        ValueAnimator.AnimatorUpdateListener { animation ->
            val animatedValue = animation?.animatedValue as? Int
            text = (mMaxTime - (animatedValue ?: 0)).toString()
        }
}