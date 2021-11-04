package com.example.customedittext.adapters

import com.example.customedittext.CustomEditText
import com.example.customedittext.R

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import androidx.databinding.adapters.TextViewBindingAdapter
import kotlinx.android.synthetic.main.custom_editext.view.*

object CustomEditTextBinders {

    /**
     * This binding adapter is used to set custom edittext value.
     *
     * @param customEditText
     * @param value
     */
    @JvmStatic
    @BindingAdapter("textValue")
    fun setTextValue(customEditText: CustomEditText, value: String?) {
        value?.let {
            customEditText.setTextValue(value)
        }
    }

    @JvmStatic
    @BindingAdapter("errorTextValue")
    fun setErrorTextValue(customEditText: CustomEditText, value: String?) {
        value?.let {
            customEditText.lableError.text = value
        }
    }

    @JvmStatic
    @BindingAdapter("isErrorEnable")
    fun setIsErrorEnable(customEditText: CustomEditText, value: Boolean) {
        customEditText.setIsErrorEnable(value)
    }

    @JvmStatic
    @BindingAdapter(value = ["android:afterTextChanged", "android:textAttrChanged"], requireAll = false)
    fun setTextWatcher(filterPositionView: CustomEditText   , test: TextViewBindingAdapter.AfterTextChanged?, textAttrChanged: InverseBindingListener?) {
        val newValue = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                test?.let {
                    test.afterTextChanged(s)
                }

                textAttrChanged?.let {
                    textAttrChanged.onChange()
                }
            }
        }
        val oldValue = ListenerUtil.trackListener(filterPositionView.editText, newValue, R.id.textWatcher)
        if (oldValue != null) {
            filterPositionView.editText.removeTextChangedListener(oldValue)
        }
        filterPositionView.editText.addTextChangedListener(newValue)
    }
}