package com.ddd.presentation.ui.main

import android.app.Dialog
import android.util.Log
import android.view.View
import com.ddd.presentation.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun getInstance(): MainBottomSheetFragment = MainBottomSheetFragment()
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        (View.inflate(context, R.layout.fragment_main_bottom_sheet, null))
            .let(dialog::setContentView)
        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheetInternal = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal)
        bottomSheetBehavior.setBottomSheetCallback(requestLayoutExpandedCallback)
    }

    private val requestLayoutExpandedCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(p0: View, p1: Float) {
            Log.e(":1", p1.toString())
        }

        override fun onStateChanged(view: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                view.run {
                    requestLayout()
                    invalidate()
                }
            }
        }
    }
}
