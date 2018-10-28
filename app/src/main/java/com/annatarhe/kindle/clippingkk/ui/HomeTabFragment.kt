package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.adapters.HomeCardItemAdapter
import com.annatarhe.kindle.clippingkk.model.ClippingItem

class HomeTabFragment : Fragment() {

    var clippings: MutableList<ClippingItem> = mutableListOf()
    private lateinit var homeClipping: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_list_fragment, container, false)

        val homeClippings = view.findViewById<RecyclerView>(R.id.homeClippings)
        val viewManager = LinearLayoutManager(activity)
        homeClippings.setHasFixedSize(true)
        homeClippings.layoutManager = viewManager
        homeClippings.adapter = HomeCardItemAdapter(clippings)

        homeClipping = homeClippings

        loadMoreData()
        return view
    }

    fun loadMoreData() {
        val item = ClippingItem("title", "content", "location", "11")
        clippings.add(item)

        homeClipping.adapter?.notifyDataSetChanged()

    }
}