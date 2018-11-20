package com.annatarhe.kindle.clippingkk

import android.app.Application
import android.content.Context
import android.util.Log
import com.annatarhe.kindle.clippingkk.model.AppConfig
import com.github.kittinunf.fuel.core.FuelManager


class ClippingKKApplication : Application() {
    init {
        FuelManager.instance.basePath = AppConfig.host
    }

    override fun onCreate() {
        super.onCreate()

        this.setupToken()
    }

    private fun setupToken() {
        val perf = this.getSharedPreferences("idWithToken", Context.MODE_PRIVATE)
        val idWithToken = perf.getString("idWithToken", "").split("|")
        if (idWithToken.size < 2) {
            return
        }
        val id = idWithToken[0]
        val token = idWithToken[1]

        AppConfig.uid = id.toInt()
        AppConfig.jwt = token

        Log.i("auth", idWithToken.toString())

        FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer ${token}")
    }

}