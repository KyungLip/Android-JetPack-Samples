package com.jetpack.samples.binding

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.jetpack.samples.R
import com.jetpack.samples.databinding.LayoutCustomBindingViewBinding

/**
 * @author liupeng
 * @version v1.0
 * @date 2023/4/14 5:01 下午
 * @Desc
 */
class CustomBindView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var viewBinding: LayoutCustomBindingViewBinding

    init {
        //1：创建绑定类的实例
        viewBinding = LayoutCustomBindingViewBinding.inflate(LayoutInflater.from(context))
//        viewBinding= LayoutCustomBindingViewBinding.inflate(LayoutInflater.from(context),this,true)
//        inflate(context, R.layout.layout_custom_binding_view, this)
//        viewBinding= LayoutCustomBindingViewBinding.bind(this)
    }
}