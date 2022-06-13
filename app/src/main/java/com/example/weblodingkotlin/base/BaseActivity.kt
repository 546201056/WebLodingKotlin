package com.example.weblodingkotlin.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
//import com.alibaba.android.arouter.launcher.ARouter
import com.example.weblodingkotlin.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<D : ViewDataBinding?> : AppCompatActivity() {


    protected var mBinding: D? = null

    /***
     * 初始化布局
     */
    @LayoutRes
    protected abstract fun setMainLayout(): Int

    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 初始化先前数据
     */
    protected abstract fun initBeforeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        ARouter.getInstance().inject(this)
        initDataBinding()
        setStatusBar()
        initView()
        initBeforeData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
//        ARouter.getInstance().inject(this)
    }

    /** DataBinding初始化 */
    private fun initDataBinding() {
        mBinding =  DataBindingUtil.setContentView<D>(this, setMainLayout())
    }



    /**
     * 弹出toast 显示时长short
     *
     * @param msg
     */
    protected fun showToastShort(msg: String?) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showToastShort(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun openIntent(intent: Intent?) {
        startActivity(intent)
    }

    protected fun openForResultActivity(intent: Intent?, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    open fun setStatusBar(color: String? = "#ffffff") {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.parseColor(color) //设置状态栏颜色
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //实现状态栏图标和文字颜色为暗色
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun hideStatusBar(isChangeStatusBarTextColor: Boolean = false) {
        //4.4版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (isChangeStatusBarTextColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


}