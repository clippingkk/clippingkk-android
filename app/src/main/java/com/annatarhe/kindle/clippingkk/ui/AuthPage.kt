package com.annatarhe.kindle.clippingkk.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.AuthAPI
import kotlinx.android.synthetic.main.activity_auth.*


class AuthPage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun handleSubmit(view: View) {
        val email = authEmail.text.toString()
        val pwd = authPwd.text.toString()
        Log.i("auth", email)
        Log.i("auth", pwd)

        // TODO: handle response
        AuthAPI().Auth(email, pwd, { user ->
            Log.i("auth", user.avatar)
            Log.i("auth", user.token)
            Log.i("auth", user.id.toString())

            runOnUiThread {
                finish()
            }
            saveToKeyStore(user)
            return@Auth null
        })
    }

    private fun saveToKeyStore(user: AuthAPI.User) {
        val pref = this.getSharedPreferences("idWithToken", Context.MODE_PRIVATE)

        val data = "${user.id}|${user.token}"
        pref.edit().putString("idWithToken", data).commit()
    }
}