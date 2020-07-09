package com.ddd.data.manager

import android.util.Log
import com.ddd.common.DDDException
import com.ddd.common.MessageManager
import com.ddd.data.entity.DataEntity
import com.ddd.domain.FirebaseRepository
import com.ddd.domain.entity.DomainEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

const val QUERY_USERS_ATTENDANCE = "attendance"
const val QUERY_USERS = "users"
const val QUERY_MANAGER = "isManager"

class FirebaseRepositoryImpl @Inject constructor(
    private val db: DatabaseReference,
    private val auth: FirebaseAuth
) : FirebaseRepository {

    override fun signOut() {
        auth.signOut()
    }

    override fun saveUser(email: String, name: String, position: String, isManager: Boolean) {
        db.child(QUERY_USERS)
            .child(auth.currentUser?.uid.orEmpty())
            .setValue(DataEntity.UserEntity(email, name, position, isManager).toMap())
    }

    override fun isManager(uuid: String, result: (Boolean) -> Unit, error: (DDDException) -> Unit) {
        db.child(QUERY_USERS)
            .child(uuid)
            .child(QUERY_MANAGER)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    error(DDDException.NotFindDataBaseData(MessageManager.ERROR_NOT_FOUND_FIREBASE_DATABASE_DATA))
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val isManager = (dataSnapshot.value as Boolean)
                    result(isManager)
                }
            })
    }

    override fun saveAttendance(
        uuid: String,
        startAttendance: String,
        realAttendance: String,
        result: (Unit) -> Unit
    ) {
        val attendanceResult = if (startAttendance.toLong() > realAttendance.toLong()) {
            // 정상 출석
            "0"
        } else {
            // 지각
            "1"
        }
        db.child(QUERY_USERS)
            .child(uuid)
            .child(QUERY_USERS_ATTENDANCE)
            .child(startAttendance)
            .setValue(DataEntity.Attendance(attendanceResult))
    }

    override fun getCurriculum(getItems: (List<DomainEntity.Curriculum>) -> Unit) {
        db.child("curriculum")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(data: DataSnapshot) {
                    val item = mutableListOf<DomainEntity.Curriculum>()
                    data.children.forEach {
                        if (it.exists()) {
                            val title = (it.child("title").value as String)
                            val isDone = (it.child("isDone").value as Boolean)
                            val date = (it.child("date").value as String)
                            DomainEntity.Curriculum(date, isDone, title).let(item::add)
                        }
                    }
                    getItems(item)
                }
            })
    }

    override fun getBannerData(getItems: (DomainEntity.Banner) -> Unit) {
        db.child("banner")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) = Unit

                override fun onDataChange(data: DataSnapshot) {
                    val title = (data.child("title").value as String)
                    val subTitle = (data.child("subTitle").value as String)
                    DomainEntity.Banner(title, subTitle).let(getItems)
                }
            })
    }

    override fun searchName(name: String,getItems: (List<DomainEntity.UserEntity>) -> Unit) {
        db.child(QUERY_USERS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) = Unit

                override fun onDataChange(p0: DataSnapshot) {

                    val result = p0.children.fold(mutableListOf<DomainEntity.UserEntity>()) { acc, dataSnapshot ->
                        Log.e("data",dataSnapshot.child("name").value.toString())
                        if(dataSnapshot.child("name").value as String == name){
                            val email = (dataSnapshot.child("email").value as String)
                            val userName = (dataSnapshot.child("name").value as String)
                            val position = (dataSnapshot.child("position").value as String)
                            val isManager = (dataSnapshot.child("isManager").value as Boolean)
                            val items = mutableListOf<DomainEntity.Attendance>()
                            val timeValue = dataSnapshot.child("attendance").value
                            if(timeValue!=null){
                                dataSnapshot.child("attendance").children.forEach {
                                    items.add(DomainEntity.Attendance(it.key?.toLong()?:0L, it.child("result").value as String))
                                }
                            }
                            acc.add(DomainEntity.UserEntity(email, userName, position, isManager,items))
                        }
                        acc
                    }
                    getItems(result)
                }
            })
    }
}
