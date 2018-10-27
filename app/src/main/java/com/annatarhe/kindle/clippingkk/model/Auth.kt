package com.annatarhe.kindle.clippingkk.model

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import com.github.salomonbrys.kotson.jsonObject

class AuthAPI {

    data class User(
        val id: Int,
        val email: String,
        val avatar: String,
        val token: String
    )

    fun Auth(email: String, pwd: String, onSuccess: (User) -> Nothing?) {
        val params = jsonObject(
            "email" to email,
            "pwd" to pwd
        )
        "https://api.clippingkk.annatarhe.com/api/auth/login".httpPost()
            .jsonBody(params.toString())
            .responseJson { request, response, result ->
                val resultData = result.get().obj()
                Log.i("auth", resultData.toString())
                if (resultData.getInt("status") != 200) {
                    Log.i("auth", resultData.toString())
                }
                val data = resultData.getJSONObject("data")
                val profile = data.getJSONObject("profile")
                val user = User(
                    profile.getInt("id"),
                    profile.getString("email"),
                    profile.getString("avatar"),
                    data.getString("token")
                )
                onSuccess(user)
            }
    }
}