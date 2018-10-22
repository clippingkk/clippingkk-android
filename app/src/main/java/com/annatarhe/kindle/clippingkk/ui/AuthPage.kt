package com.annatarhe.kindle.clippingkk.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
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
        AuthAPI().Auth(email, pwd)
    }
}