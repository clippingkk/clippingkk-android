package com.annatarhe.kindle.clippingkk.model

import com.annatarhe.kindle.clippingkk.BuildConfig

class AppConfig {
    companion object {
        var jwt: String = ""
        var uid: Int = -1
//
//        var jwt: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MX0.5PpqJsOWOV7JWkFAheOHswZ4P7QcPMyTUlqKeyUHWGY"
//        var uid: Int = 1

        val host =
            if (BuildConfig.DEBUG) "https://api.clippingkk.annatarhe.com/api" else "https://api.clippingkk.annatarhe.com/api"
    }

}