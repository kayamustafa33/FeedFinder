package com.mobile.feedfinder.interfaces

import android.content.Context
import com.mobile.feedfinder.model.User

interface UserITF {

    fun createUser(context : Context, user : User)
    fun loginUser(context: Context,user: User)
    fun takeUserData(userID : String?,callback : (User?) -> Unit)
}