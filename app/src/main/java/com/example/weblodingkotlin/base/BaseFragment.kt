package com.example.weblodingkotlin.base

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

abstract class BaseFragment :Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBeforeData()
    }


    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 初始化先前数据
     */
    protected abstract fun initBeforeData()


    fun getStatusBarHeight(): Int {
        var result = 0
        var resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result
    }

    protected open fun setStatusBar(color: String? = "#ffffff") {
        (activity as? BaseActivity<*>)?.setStatusBar(color) ?: fun() {
            activity?.window?.statusBarColor = Color.parseColor(color)
        }.invoke()
    }


}