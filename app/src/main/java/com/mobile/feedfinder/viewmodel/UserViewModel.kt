package com.mobile.feedfinder.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mobile.feedfinder.interfaces.UserITF
import com.mobile.feedfinder.model.User
import com.mobile.feedfinder.service.FirebaseImplementor
import com.mobile.feedfinder.util.CustomProgressDialog
import com.mobile.feedfinder.util.goActivityWithFlags
import com.mobile.feedfinder.util.showToast
import com.mobile.feedfinder.view.MainActivity

class UserViewModel : ViewModel() , UserITF {

    val userData = MutableLiveData<User?>()
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
                    showToast(context,"User exist!")
                }
            }.addOnFailureListener {
                customProgressDialog?.dismiss()
                // Set it again for network errors
                showToast(context,"Check your connection!")
            }
        }
    }

    override fun loginUser(context: Context, user: User) {
        customProgressDialog = CustomProgressDialog(context)
        customProgressDialog?.show()
        with(initFirebase){
            initAuthAndUser()
            firebaseAuth?.signInWithEmailAndPassword(user.email,user.password)!!.addOnCompleteListener {
                if(it.isSuccessful){
                    userData.value = user
                    goActivityWithFlags(context,MainActivity::class.java)
                    customProgressDialog?.dismiss()
                } else{
                    // Display error message
                    showToast(context,"User not found!")
                    customProgressDialog?.dismiss()
                }
            }.addOnFailureListener {
                // Display error message
                showToast(context,"Check your internet connection!")
                customProgressDialog?.dismiss()
            }
        }
    }

    override fun takeUserData(userID : String? , callback: (User?) -> Unit) {
        with(initFirebase){
            initAuthAndUser()
            createOrOpenDatabase("Users")

            val userListener = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val email = snapshot.child("email").value.toString()
                        val password = snapshot.child("password").value.toString()

                        val user = User(email,password)
                        callback(user).apply {
                            userData.value = user
                        }
                    } else {
                        callback(null).apply {
                            userData.value = null
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null).apply {
                        userData.value = null
                    }
                }

            }

            userID?.let { databaseReference?.child(it) }!!.addValueEventListener(userListener)
        }
    }

    private fun registerOnRealTimeDB(context: Context, user: User) {
        with(initFirebase){
            initAuthAndUser()
            createOrOpenDatabase("Users")
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