package com.mobile.feedfinder.service

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mobile.feedfinder.authentication.AuthenticationActivity
import com.mobile.feedfinder.interfaces.FirebaseInitializer
import com.mobile.feedfinder.util.goActivityWithFlags

class FirebaseImplementor : FirebaseInitializer{

    var firebaseAuth: FirebaseAuth? = null
    var firebaseUser: FirebaseUser? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var firebaseStorage: FirebaseStorage? = null

    override fun initAuthAndUser() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth?.let {
            firebaseUser = it.currentUser
        }
    }

    override fun createOrOpenDatabase(dbName : String) {
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase?.let {
            databaseReference = it.getReference(dbName)
        }
    }

    override fun createOrOpenStorage() {
        firebaseStorage = FirebaseStorage.getInstance()
    }

    override fun isLogin(): Boolean {
        initAuthAndUser()
        return firebaseAuth?.currentUser != null
    }

    override fun logOut(context: Context) {
        initAuthAndUser()
        firebaseAuth?.signOut().apply {
            goActivityWithFlags(context,AuthenticationActivity::class.java)
        }
    }

    override fun takeCurrentUserID(): String? {
        initAuthAndUser()
        return firebaseUser?.uid
    }


}