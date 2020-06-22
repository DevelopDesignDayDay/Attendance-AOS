package com.ddd.presentation.ui.main

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.CurriCulumUseCase
import com.ddd.domain.GetBannerUseCase
import com.ddd.domain.GetQRCodeDataUseCase
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.ui.tutorial.TutorialActivity
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getQRCodeDataUseCase: GetQRCodeDataUseCase,
    curriCulumUseCase: CurriCulumUseCase,
    getBannerUseCase: GetBannerUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    sealed class Result {
        data class Banner(val title: String, val subTitle: String) : Result()
        data class InitQRCode(val qrcode: Bitmap) : Result()
        data class Curriculum(val items: List<DomainEntity.Curriculum>) : Result()
        data class LoginActivity<T>(val nextActivity: Class<T>) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    init {
        getQRCodeDataUseCase.execute(
            success = {
                _liveResult.value = Result.InitQRCode(it)
            },
            error = {
                _liveResult.value = Result.LoginActivity(TutorialActivity::class.java)
            }
        )
        curriCulumUseCase {
            _liveResult.value = Result.Curriculum(it)
        }
        getBannerUseCase.invoke {
            Log.e("title",it.title)
            Log.e("subTitle",it.subTitle)
            _liveResult.value = Result.Banner(it.title,it.subTitle)
        }
    }

    fun tempLogout() {
        auth.signOut()
    }

}
