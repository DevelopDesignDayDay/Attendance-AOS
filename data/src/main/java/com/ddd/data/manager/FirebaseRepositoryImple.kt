package com.ddd.data.manager

import com.ddd.common.DDDException
import com.ddd.common.MessageManager.ERROR_NOT_FOUND_FIREBASE_DATABASE_DATA
import com.ddd.data.entity.DataEntity
import com.ddd.domain.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

const val QUERY_USERS_ATTENDANCE = "attendance"
const val QUERY_USERS = "users"
const val QUERY_MANAGER = "manager"

class FirebaseRepositoryImpl @Inject constructor(
    private val db: DatabaseReference,
    private val auth: FirebaseAuth
) : FirebaseRepository {
    override fun saveUser(email: String, name: String, position: String, isManager: Boolean) {
        val user = DataEntity.UserEntity(email, name, position, isManager)
        db.child(QUERY_USERS).child(auth.currentUser?.uid.orEmpty()).setValue(user)
    }

    override fun isManager(uuid: String, result: (Boolean) -> Unit, error: (DDDException) -> Unit) {
        db.child(QUERY_USERS)
            .child(uuid)
            .child(QUERY_MANAGER)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) =
                    error(DDDException.NotFindDataBaseData(ERROR_NOT_FOUND_FIREBASE_DATABASE_DATA))

                override fun onDataChange(dataSnapshot: DataSnapshot) =
                    result(dataSnapshot.value as Boolean)
            })
    }

    override fun saveAttendance(
        uuid: String,
        place:String,
        startAttendance: String,
        realAttendance: String,
        result: (Unit) -> Unit
    ) {
        db.child(QUERY_USERS)
            .child(uuid)
            .child(QUERY_USERS_ATTENDANCE)
            .child(startAttendance)
            .setValue(DataEntity.Attendance(place,realAttendance))
        result(Unit)
    }
}
