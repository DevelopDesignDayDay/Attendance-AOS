package com.ddd.presentation.ui.manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.ddd.common.createViewModel
import com.ddd.common.ob
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityMoveCheckAttendanceBinding
import kotlinx.android.synthetic.main.activity_move_check_attendance.*
import javax.inject.Inject

class MoveCheckAttendance :
    BaseActivity<MoveCheckAttendanceViewModel, ActivityMoveCheckAttendanceBinding>() {
    override val layoutResource: Int
        get() = R.layout.activity_move_check_attendance

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: MoveCheckAttendanceViewModel by lazy {
        createViewModel(factory, MoveCheckAttendanceViewModel::class.java)
    }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            viewModel = this@MoveCheckAttendance.viewModel
            lifecycleOwner = this@MoveCheckAttendance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_check_attendance)
        ob(viewModel.liveResult, result)
        search_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchName(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private val result: (MoveCheckAttendanceViewModel.Result) -> Unit = { result ->
        when (result) {
            is MoveCheckAttendanceViewModel.Result.EmptyName -> {
                Log.e("EmptyName", "EmptyName")
            }
            is MoveCheckAttendanceViewModel.Result.Data -> {
                val items = result.items
                if (items.isNullOrEmpty()) {

                } else {
                    rv.adapter = CheckAttendanceAdapter().also {
                        it.setItems(items)
                    }
                }
            }
        }
    }
}