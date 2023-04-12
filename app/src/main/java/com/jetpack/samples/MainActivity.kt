package com.jetpack.samples

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.samples.anim.AnimFragment
import com.jetpack.samples.lifecyle.LifecycleFragment
import com.jetpack.samples.lifecyle.MiniReportFragment
import com.jetpack.samples.lifecyle.MyLifecycleOwner
import com.jetpack.samples.utils.logI

class MainActivity : AppCompatActivity() {
    private val dataList = mutableListOf<String>("Anim","Lifecycle")
    private  val myLifecycleOwner= MyLifecycleOwner()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        initView()
//        lifecycle.addObserver(MainActivityLifecycleObserver())
//        myLifecycleOwner.lifecycleRegistry.addObserver(MainActivityLifecycleObserver())
//        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        logI("MainActivity-onCreate")
        MiniReportFragment.injectIfNeededIn(this)

    }

    private var mSupportFragmentManager: FragmentManager? = null
    private fun initView() {
        val rvRecyclerView = findViewById<RecyclerView>(R.id.rv_recycler_view)
        rvRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val mainAdapter = MainAdapter()
        rvRecyclerView.adapter = mainAdapter
        mainAdapter.setData(dataList)
        mSupportFragmentManager = supportFragmentManager
        mainAdapter.setOnItemClickListener(object :
            MainAdapter.OnItemClickListener {
            override fun onItemClick(index: Int, data: String) {
                when (index) {
                    0 -> {
                        val transaction = mSupportFragmentManager?.beginTransaction()
                        transaction?.add(R.id.fl_container, AnimFragment())
                        transaction?.addToBackStack("Anim")
                        transaction?.commit()
                    }
                    1->{
                        val transaction = mSupportFragmentManager?.beginTransaction()
                        transaction?.add(R.id.fl_container, LifecycleFragment())
                        transaction?.addToBackStack("Lifecycle")
                        transaction?.commit()
                    }
                }
            }
        })
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if ((mSupportFragmentManager?.backStackEntryCount ?: 0) >= 1) {
                    mSupportFragmentManager?.popBackStack()
                    return
                }
                finish()
            }
        })
    }

    class MainAdapter : RecyclerView.Adapter<MainAdapter.VH>() {

        class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

        private val dataList = mutableListOf<String>()

        @SuppressLint("NotifyDataSetChanged")
        fun setData(list: List<String>) {
            dataList.clear()
            dataList.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MainAdapter.VH {
            val textView = TextView(parent.context)
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)
            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(Color.GRAY)
            textView.layoutParams = layoutParams
            return MainAdapter.VH(textView)
        }

        override fun onBindViewHolder(holder: MainAdapter.VH, position: Int) {
            val textView = holder.itemView as? TextView
            textView?.text = dataList[position]
            textView?.setOnClickListener {
                onItemClickListener?.onItemClick(position, data = dataList[position])
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        private var onItemClickListener:MainAdapter.OnItemClickListener? = null

        fun setOnItemClickListener(onItemClickListener: MainAdapter.OnItemClickListener?) {
            this.onItemClickListener = onItemClickListener
        }

        interface OnItemClickListener {
            fun onItemClick(index: Int, data: String)
        }
    }

    override fun onStart() {
        super.onStart()
        logI("MainActivity-onStart")
        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onRestart() {
        super.onRestart()
        logI("MainActivity-onRestart")
    }

    override fun onResume() {
        super.onResume()
        logI("MainActivity-onResume")
        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        logI("MainActivity-onPause")
        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        logI("MainActivity-onStop")
        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        logI("MainActivity-onDestroy")
        myLifecycleOwner.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}

