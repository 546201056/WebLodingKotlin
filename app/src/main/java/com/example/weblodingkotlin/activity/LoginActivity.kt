package com.example.weblodingkotlin.activity

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
//import com.alibaba.android.arouter.facade.annotation.Route
//import com.alibaba.android.arouter.utils.TextUtils
import com.example.weblodingkotlin.R
import com.example.weblodingkotlin.base.BaseActivity
import com.example.weblodingkotlin.databinding.LoginActivityBinding
import com.example.weblodingkotlin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_activity.*


//@Route(path = "/page/login")
class LoginActivity : BaseActivity<LoginActivityBinding>() {

    private lateinit var viewModel: LoginViewModel

    override fun setMainLayout(): Int {
        return R.layout.login_activity
    }

    override fun initView() {
        hideStatusBar()
        val sp = getSharedPreferences("config", MODE_PRIVATE)
        when (sp.getInt("fastpage", -1)) {
            2 -> {
                //token 不为空的时候，是第一次登录
//                ARouter.getInstance().build("/page/dispatching").navigation()
                finish()
            }
            else -> {

            }
        }
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.loginLiveData.observe(this) {
//           sp.edit().putString("token",it).commit()
//            ARouter.getInstance().build("/page/dispatching").navigation()
            }
    }


    override fun initBeforeData() {
        login_btn.setOnClickListener {
            validateEidt()
            viewModel.login(login_name.text.toString(),login_password.text.toString())
        }
    }

    private fun validateEidt() {
        if (TextUtils.isEmpty(login_name.text)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(login_password.text)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

    }

}