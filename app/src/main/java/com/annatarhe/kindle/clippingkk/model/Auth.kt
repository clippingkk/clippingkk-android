package com.annatarhe.kindle.clippingkk.model

import android.app.DownloadManager
import android.util.Log
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.salomonbrys.kotson.jsonObject

class AuthAPI {

    data class User(
        val email: String,
        val avatar: String
    )

    fun Auth(email: String, pwd: String) {
        val params = jsonObject(
            "email" to email,
            "pwd" to pwd
        )
        "https://api.clippingkk.annatarhe.com/api/auth".httpPost()
            .jsonBody(params.toString())
            .responseJson { request, response, result ->
                val resultData = result.get().obj()
                Log.i("auth", resultData.toString())
            }
    }
}