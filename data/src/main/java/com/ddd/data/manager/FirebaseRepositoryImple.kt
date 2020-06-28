package com.ddd.data.manager

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
    override fun saveUser(email: String, name: String, position: String, isManager: Boolean) {
        db.child(QUERY_USERS)
            .child(auth.currentUser?.uid.orEmpty())
            .setValue(DataEntity.UserEntity(email, name, position, isManager).toMap())
    }

    override fun isManager(uuid: String, result: (Boolean) -> Unit, error: (DDDException) -> Unit) {
        db.child(QUERY_USERS)
            .child(uuid)
            .child(QUERY_MANAGER)
            .addListenerForSingleValueEvent(object : ValueEventListener {
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
            .addListenerForSingleValueEvent(object : ValueEventListener {
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
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(data: DataSnapshot) {
                    val title = (data.child("title").value as String)
                    val subTitle = (data.child("subTitle").value as String)
                    DomainEntity.Banner(title, subTitle).let(getItems)
                }
            })
    }
}
