package com.jetpack.samples.anim

import android.animation.Animator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.jetpack.samples.R
import com.jetpack.samples.product.TimeView

class AnimFragment : Fragment(), View.OnClickListener {
    private lateinit var mFlArea: FrameLayout
    private lateinit var mTvArea: TextView
    private var mFrameAnimationDrawable: AnimationDrawable? = null
    private var mTweenAnimation: Animation? = null
    private var mPropertyAnimator: Animator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anim, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        mFlArea = view.findViewById(R.id.fl_anim_area)
        mTvArea = view.findViewById(R.id.tv_anim_area)
        view.findViewById<Button>(R.id.btn_frame_start).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_frame_stop).setOnClickListener(this)

        view.findViewById<Button>(R.id.btn_rotate_anim).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_scale_anim).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_translate_anim).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_alpha_anim).setOnClickListener(this)

        view.findViewById<Button>(R.id.btn_rotate_animator).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_scale_animator).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_translate_animator).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_alpha_animator).setOnClickListener(this)
        val tvTime = view.findViewById<TimeView>(R.id.tv_time)
        tvTime.setOnClickListener {
//            tvTime.startTimer(10)
            PropertyController.createViewPropertyAnimator(tvTime)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_frame_start -> startFrameAnim()
            R.id.btn_frame_stop -> mFrameAnimationDrawable?.stop()

            R.id.btn_rotate_anim -> executeTweenAnim { TweenController.createRotateAnim() }
            R.id.btn_scale_anim -> executeTweenAnim { TweenController.createScaleAnim() }
            R.id.btn_translate_anim -> executeTweenAnim { TweenController.createTranslateAnim() }
            R.id.btn_alpha_anim -> executeTweenAnim { TweenController.createAlphaAnim() }

            R.id.btn_rotate_animator -> executePropertyAnimator {
                PropertyController.createRotateAnimator(
                    it
                )
            }
            R.id.btn_scale_animator -> executePropertyAnimator {
                PropertyController.createScaleAnimator(
                    it
                )
            }
            R.id.btn_translate_animator -> executePropertyAnimator {
                PropertyController.createTranslateAnimator(
                    it
                )
            }
            R.id.btn_alpha_animator -> executePropertyAnimator {
                PropertyController.createAlphaAnimator(
                    it
                )
            }

        }
    }

    private fun startFrameAnim() {
        mFrameAnimationDrawable?.stop()
        if (mFrameAnimationDrawable == null) {
            mFrameAnimationDrawable = FrameController.createFrameAnim()
            mFlArea.background = mFrameAnimationDrawable
        }
        mFrameAnimationDrawable?.start()
    }

    private inline fun executeTweenAnim(creator: () -> Animation) {
        mTweenAnimation?.cancel()
        mTweenAnimation = creator.invoke()
        mTvArea.startAnimation(mTweenAnimation)
    }

    private inline fun executePropertyAnimator(creator: (view: View) -> Animator) {
        mPropertyAnimator?.end()
        mPropertyAnimator = creator.invoke(mTvArea)
        mPropertyAnimator?.start()
    }

}