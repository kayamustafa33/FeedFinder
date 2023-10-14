package com.mobile.feedfinder.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mobile.feedfinder.interfaces.FirebaseInitializer

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

    override fun createDatabase(dbName : String) {
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase?.let {
            databaseReference = it.getReference(dbName)
        }
    }

    override fun createStorage() {
        firebaseStorage = FirebaseStorage.getInstance()
    }

    override fun isLogin(): Boolean {
        initAuthAndUser()
        return firebaseAuth?.currentUser != null
    }


}