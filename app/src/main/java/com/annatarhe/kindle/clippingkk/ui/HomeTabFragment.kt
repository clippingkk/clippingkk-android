package com.annatarhe.kindle.clippingkk.ui

import android.content.Intent
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
        val adapter = HomeCardItemAdapter(clippings)
        adapter.setOnClickListener(object : HomeCardItemAdapter.OnItemClickListener {
            override fun onClick(v: View, data: ClippingAPI.ClippingItem) {
                Log.i("detail", data.toString())
                val intent = Intent(this@HomeTabFragment.context, DetailPage::class.java)
                intent.putExtra(DetailPage.DETAIL_KEY, data.id)
                startActivity(intent)
            }
        })
        homeClippings.setHasFixedSize(true)
        homeClippings.layoutManager = viewManager
        homeClippings.adapter = adapter

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