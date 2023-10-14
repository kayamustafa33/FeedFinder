package com.mobile.feedfinder.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.feedfinder.interfaces.UserITF
import com.mobile.feedfinder.model.User
import com.mobile.feedfinder.service.FirebaseImplementor
import com.mobile.feedfinder.util.CustomProgressDialog
import com.mobile.feedfinder.util.goActivityWithFlags
import com.mobile.feedfinder.view.MainActivity

class UserViewModel : ViewModel() , UserITF {

    val userData = MutableLiveData<User>()
    private val initFirebase = FirebaseImplementor()

    private var customProgressDialog : CustomProgressDialog? = null

    override fun createUser(context : Context, user : User) {
        customProgressDialog = CustomProgressDialog(context)
        customProgressDialog?.show()
        with(initFirebase){
            initAuthAndUser()
            firebaseAuth!!.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    initAuthAndUser()
                    firebaseUser?.let {
                        registerOnRealTimeDB(context,user)
                    }
                } else {
                    customProgressDialog?.dismiss()
                }
            }.addOnFailureListener {
                customProgressDialog?.dismiss()
            }
        }
    }

     private fun registerOnRealTimeDB(context: Context, user: User) {
        with(initFirebase){
            initAuthAndUser()
            createDatabase("Users")
            databaseReference?.child(firebaseUser!!.uid)!!.setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    userData.value = user
                    goActivityWithFlags(context, MainActivity::class.java)
                    customProgressDialog?.dismiss()
                } else {
                  // Return error message
                    customProgressDialog?.dismiss()
                }
            }.addOnFailureListener {
                // Return error message
                customProgressDialog?.dismiss()
            }
        }
    }


}