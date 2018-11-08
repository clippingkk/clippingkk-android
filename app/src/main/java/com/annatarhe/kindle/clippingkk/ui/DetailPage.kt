package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.annatarhe.kindle.clippingkk.R

class DetailPage : AppCompatActivity() {
    companion object {
        val DETAIL_KEY = "DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        val clippingID = intent.extras.getInt(DetailPage.DETAIL_KEY)
        Log.i("detail", clippingID.toString())
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }
}