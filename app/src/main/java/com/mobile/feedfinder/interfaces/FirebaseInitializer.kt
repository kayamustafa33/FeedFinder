package com.mobile.feedfinder.interfaces

interface FirebaseInitializer {

    fun initAuthAndUser()
    fun createDatabase(dbName : String)
    fun createStorage()
    fun isLogin() : Boolean
}