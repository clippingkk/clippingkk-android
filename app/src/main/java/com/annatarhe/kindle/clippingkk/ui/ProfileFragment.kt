package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annatarhe.kindle.clippingkk.R
import kotlinx.android.synthetic.main.profile_fragment.view.*

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }
}