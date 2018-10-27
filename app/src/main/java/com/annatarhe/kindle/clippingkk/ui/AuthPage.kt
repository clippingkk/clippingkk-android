package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.AuthAPI
import kotlinx.android.synthetic.main.activity_auth.*
import java.security.KeyStore

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
            saveToKeyStore(user)
            return@Auth null
        })
    }

    private fun saveToKeyStore(user: AuthAPI.User) {
        val keystore = KeyStore.getInstance("AndroidKeyStore")
        // TODO: save to keystore
        // https://developer.android.com/training/articles/keystore
    }
}