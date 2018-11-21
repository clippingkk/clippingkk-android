package com.annatarhe.kindle.clippingkk.model

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson

class VersionAPI {

    data class VersionInfo(
        val id: Int,
        val platform: String,
        val version: Int,
        val info: String,
        val url: String,
        val createdAt: String
    )


    fun GetLastVersion(onSuccess: (VersionInfo?) -> Unit, onFail: (msg: String) -> Unit) {
        "/api/version/Android?take=1".httpGet().responseJson { request, response, result ->
            if (response.statusCode != 200) {
                onFail(response.responseMessage)
                return@responseJson
            }
            val resultObj = result.get().obj()
            if (resultObj.getInt("status") != 200) {
                onFail(resultObj.getString("msg"))
                return@responseJson
            }
            val data = resultObj.getJSONArray("data")

            val versionList = Gson().fromJson<List<VersionInfo>>(data.toString())

            onSuccess(versionList[0])
            return@responseJson
        }
    }
}