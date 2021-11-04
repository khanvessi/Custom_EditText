package com.example.customedittext.main

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.customedittext.R
import com.example.customedittext.databinding.MainActivityBinding
import com.example.customedittext.extensions.watch

class MainActivity() : BaseActivity<MainActivityBinding, MainActivityViewModel>() {
    override val mViewModel: MainActivityViewModel
        get() = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        bindObject.clickHandler = this
    }

    override fun onClick(v: View?) {

    }

    override fun initializeObservers() {
        mViewModel.formValidationLiveData.watch(this) { message ->
            message.run {
                when {
                    formIsValid != 0 -> {
                        Toast.makeText(this@MainActivity, "Validation Success", Toast.LENGTH_LONG).show()
                    }
                }
            }
            bindObject.formMessage = message
        }
    }
}