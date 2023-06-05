package com.jetpack.samples.binding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jetpack.samples.R
import com.jetpack.samples.databinding.ActivityBindingDemoBinding
import com.jetpack.samples.databinding.FragmentBindingDemoBinding

class BindingDemoFragment : Fragment() {
    private lateinit var mBinding: FragmentBindingDemoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //1：创建绑定类的实例
//        //1.1
//        mBinding = FragmentBindingDemoBinding.inflate(layoutInflater)
        //1.2
        mBinding = FragmentBindingDemoBinding.inflate(layoutInflater, container, false)
//        //1.3
//        val rootVIew = layoutInflater.inflate(R.layout.fragment_binding_demo, null, false)
//        mBinding = FragmentBindingDemoBinding.bind(rootVIew)

        //2：返回根视图
        return mBinding.root
    }
}