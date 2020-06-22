package com.ddd.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ddd.common.R
import kotlinx.android.synthetic.main.custom_sign_up_position_bg.view.*


class CustomSignUpPositionView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        initView()
        getAttrs(attributeSet, defStyle)
    }

    private fun initView() {
        val service = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(service) as LayoutInflater
        addView(li.inflate(R.layout.custom_sign_up_position_bg, this, false))
    }

    private fun getAttrs(attrs: AttributeSet) {
        (context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSignUpPositionView
        )).let(::setTypeArray)
    }

    @SuppressLint("Recycle")
    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        (context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSignUpPositionView,
            defStyle,
            0
        )).let(::setTypeArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        (typedArray.getResourceId(
            R.styleable.CustomSignUpPositionView_positionLogo,
            R.drawable.sign_up_off_position_card_background
        )).let(img_position_bg::setBackgroundResource)

        (typedArray.getString(R.styleable.CustomSignUpPositionView_position)).let(tv_position_name::setText)

        typedArray.recycle()
    }

    fun setChecked() {
        img_check.isSelected = !img_check.isSelected
        container_bg.isSelected = !container_bg.isSelected
    }

    fun initialize() {
        img_check.isSelected = false
        container_bg.isSelected = false
    }
}