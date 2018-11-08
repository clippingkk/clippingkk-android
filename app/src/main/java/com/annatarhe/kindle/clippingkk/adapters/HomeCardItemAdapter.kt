package com.annatarhe.kindle.clippingkk.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.ClippingAPI

class HomeCardItemAdapter(private val clippings: List<ClippingAPI.ClippingItem>) :
    RecyclerView.Adapter<HomeCardItemAdapter.CardViewHolder>() {

    interface OnItemClickListener {
        fun onClick(view: View, data: ClippingAPI.ClippingItem)
    }

    lateinit var listener: OnItemClickListener

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
        this.listener = listener
        return CardViewHolder(containerView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = clippings.get(position)

        holder.cardBookName.text = data.title
        holder.cardContent.text = data.content

        holder.itemView.setOnClickListener {
            listener.onClick(it, data)
        }
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = clippings.size
}