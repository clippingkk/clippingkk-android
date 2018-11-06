package com.annatarhe.kindle.clippingkk.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.ClippingAPI

class HomeCardItemAdapter(private val clippings: List<ClippingAPI.ClippingItem>) :
    RecyclerView.Adapter<HomeCardItemAdapter.CardViewHolder>() {

    class CardViewHolder(containerView: CardView) : RecyclerView.ViewHolder(containerView) {
        val cardContent: TextView
        val cardBookName: TextView

        init {
            this.cardContent = containerView.findViewById<TextView>(R.id.clippingCardContent)
            this.cardBookName = containerView.findViewById<TextView>(R.id.clippingCardBookname)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCardItemAdapter.CardViewHolder {
        val containerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_clipping_card, parent, false) as CardView
        return CardViewHolder(containerView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = clippings.get(position)

        holder.cardBookName.text = data.title
        holder.cardContent.text = data.content
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = clippings.size
}