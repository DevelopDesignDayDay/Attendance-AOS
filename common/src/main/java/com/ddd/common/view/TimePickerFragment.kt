package com.ddd.common.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment(private val onTimeSet: (Int, Int) -> Unit) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(timePicker: TimePicker?, hour: Int, min: Int) {
        onTimeSet(hour, min)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Calendar.getInstance().run {
            (get(Calendar.HOUR_OF_DAY) to get(Calendar.MINUTE))
        }.let { (hour, min) ->
            TimePickerDialog(context, this, hour, min, DateFormat.is24HourFormat(context))
        }
    }
}

