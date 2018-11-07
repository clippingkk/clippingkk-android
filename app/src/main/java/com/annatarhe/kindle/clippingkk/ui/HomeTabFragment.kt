package com.annatarhe.kindle.clippingkk.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.adapters.HomeCardItemAdapter
import com.annatarhe.kindle.clippingkk.model.ClippingAPI
import kotlinx.android.synthetic.main.home_list_fragment.*
import java.util.*
import kotlin.concurrent.schedule

class HomeTabFragment : Fragment() {

    var clippings: MutableList<ClippingAPI.ClippingItem> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(activity)
        homeClippings.setHasFixedSize(true)
        homeClippings.layoutManager = viewManager
        homeClippings.adapter = HomeCardItemAdapter(clippings)

        homeSwipeRefresh.setOnRefreshListener {
            loadMoreData(0)
        }
        this.initLoad()
    }

    private fun initLoad() {
        Timer("initLoad", false).schedule(100) {
            loadMoreData(0)
        }
    }

    fun loadMoreData(offset: Int) {
        ClippingAPI().fetch(offset, { results ->
            clippings.addAll(results)
            homeClippings.adapter?.notifyDataSetChanged()

            if (homeSwipeRefresh.isRefreshing) {
                homeSwipeRefresh.isRefreshing = false
            }
        }, { msg ->
            Log.i("clipping", msg)
            Snackbar.make(this.view!!, msg, Snackbar.LENGTH_LONG).show()
        })
    }
}