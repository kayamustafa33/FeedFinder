package com.mobile.feedfinder.interfaces

import android.content.Context

interface FirebaseInitializer {

    fun initAuthAndUser()
    fun createOrOpenDatabase(dbName : String)
    fun createOrOpenStorage()
    fun isLogin() : Boolean
    fun logOut(context: Context)
    fun takeCurrentUserID() : String?
}