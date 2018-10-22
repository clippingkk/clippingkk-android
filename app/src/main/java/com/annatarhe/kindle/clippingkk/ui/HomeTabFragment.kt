package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annatarhe.kindle.clippingkk.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeTabFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_list_fragment, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}