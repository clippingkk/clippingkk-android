package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.AppConfig
import com.annatarhe.kindle.clippingkk.model.AuthAPI
import kotlinx.android.synthetic.main.activity_auth.*
import java.security.KeyStore
import java.security.Signature


class AuthPage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun handleSubmit(view: View) {
        val email = authEmail.text.toString()
        val pwd = authPwd.text.toString()
        Log.i("auth", email)
        Log.i("auth", pwd)

        // TODO: handle response
        AuthAPI().Auth(email, pwd, { user ->
            Log.i("auth", user.avatar)
            Log.i("auth", user.token)
            Log.i("auth", user.id.toString())

            AppConfig.jwt = user.token
            AppConfig.uid = user.id
            runOnUiThread {
                finish()
            }
//            saveToKeyStore(user)
            return@Auth null
        })
    }

    private fun saveToKeyStore(user: AuthAPI.User) {
        val s = _getKeyStore()
    }

    private fun _getKeyStore(): Signature {
        val ks = KeyStore.getInstance("AndroidKeyStore")
        // TODO: save to keystore
        // https://developer.android.com/training/articles/keystore

        ks.load(null)
        val aliases = ks.aliases()
        ks.load(null)
        val entry = ks.getEntry("token", null)
        if (entry !is KeyStore.PrivateKeyEntry) {
            Log.i("auth", "not an keystore.privateKeyEntry instance")
        }
        val s = Signature.getInstance("SHA256withECDSA")

        s.initSign((entry as KeyStore.PrivateKeyEntry).privateKey)
//        s.update(user.token.toByte())
        val signature = s.sign()
        return s
    }
}