package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.AppConfig
import com.annatarhe.kindle.clippingkk.model.AuthAPI
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_fragment.*


class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.loadProfile()

    }

    private fun loadProfile() {
        AuthAPI().LoadProfile(AppConfig.uid, { profileTiny ->
            Log.i("profile", profileTiny.toString())
            Picasso.get().load(profileTiny.user.avatar).into(userAvatar)

            userName.text = profileTiny.user.email
        }, { msg ->
            Log.i("profile", msg)
            Snackbar.make(this.view!!, msg, Snackbar.LENGTH_LONG).show()
        })
    }
}