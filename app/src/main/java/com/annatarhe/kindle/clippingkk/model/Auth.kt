package com.annatarhe.kindle.clippingkk.model

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson

class AuthAPI {

    data class User(
        val id: Int,
        val email: String,
        val avatar: String,
        val token: String
    )

    data class ProfileTiny(
        val user: User,
        val clippingsCount: Int,
        val clippings: List<ClippingAPI.ClippingItem>
    )

    fun Auth(email: String, pwd: String, onSuccess: (User) -> Nothing?) {
        val params = jsonObject(
            "email" to email,
            "pwd" to pwd
        )
        "/auth/login".httpPost()
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
                AppConfig.jwt = user.token
                AppConfig.uid = user.id
                FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer ${AppConfig.jwt}")

                onSuccess(user)
            }
    }

    fun LoadProfile(uid: Int, onSuccess: (ProfileTiny) -> Unit, onFail: (msg: String) -> Unit) {
        "/auth/${uid}".httpGet().responseJson { request, response, result ->
            if (response.statusCode != 200) {
                onFail(response.responseMessage)
                return@responseJson
            }
            val resultObj = result.get().obj()
            if (resultObj.getInt("status") != 200) {
                onFail(resultObj.getString("msg"))
                return@responseJson
            }

            val profile = Gson().fromJson<ProfileTiny>(resultObj.getJSONObject("data").toString())

            onSuccess(profile)
        }
    }
}