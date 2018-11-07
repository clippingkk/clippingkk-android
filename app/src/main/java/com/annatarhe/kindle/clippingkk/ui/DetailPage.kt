package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.annatarhe.kindle.clippingkk.R

class DetailPage : AppCompatActivity() {

    companion object {
        val DETAIL_KEY = "DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)
        // FIXME: not works
        val clippingID = intent.extras.getInt(DetailPage.DETAIL_KEY)
        Log.i("detail", clippingID.toString())
    }
}