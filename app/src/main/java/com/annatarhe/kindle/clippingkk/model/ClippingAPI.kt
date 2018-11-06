package com.annatarhe.kindle.clippingkk.model

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson

class ClippingAPI {
    data class ClippingItem(
        val id: Int,
        val title: String,
        val content: String,
        val pageAt: String,
        val bookID: Int,
        val seq: Int,
        val createdBy: Int
    )

    fun fetch(offset: Int, onSuccess: (List<ClippingItem>) -> Unit, onFail: (msg: String) -> Unit) {
        "/clippings/clippings/${AppConfig.uid}?take=20&from=${offset}".httpGet()
            .responseJson { request, response, result ->
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

                val clippings = Gson().fromJson<List<ClippingItem>>(data.toString())

                if (clippings == null) {
                    onFail(resultObj.getString("msg"))
                    return@responseJson
                }
                Log.i("clippings", clippings.toString())

                onSuccess(clippings)
            }
    }
}
