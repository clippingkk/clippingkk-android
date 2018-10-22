package com.annatarhe.kindle.clippingkk.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.annatarhe.kindle.clippingkk.R

class AuthPage: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth);
    }
}