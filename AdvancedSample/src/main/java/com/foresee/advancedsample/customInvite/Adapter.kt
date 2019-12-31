package com.foresee.advancedsample.customInvite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.foresee.advancedsample.R


class Adapter (private val cardList: List<CardData>): RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return when (DataType.fromInt(viewType)) {
            DataType.INVITE_TYPE -> {
                val mView = LayoutInflater.from(parent.context).inflate(R.layout.invite_card, parent, false)
                InviteCardViewHolder(mView)
            }
            DataType.PRODUCT_TYPE  -> {
                val mView = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
                ProductCardViewHolder(mView)
            }
            else -> {
                print("We shouldn't be here!")
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemCount(): Int {
        return this.cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = this.cardList[position]
        when (holder) {
            is ProductCardViewHolder -> {
                val card = currentCard as ProductCardData
                holder.imageView?.setImageResource(card.image)
                holder.titleView?.text = card.title
            }
            is InviteCardViewHolder -> {
                val card = currentCard as InviteCardData
                holder.titleView?.text = card.title
                holder.descriptionView?.text = card.description
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return this.cardList[position].type
    }
}

open class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class ProductCardViewHolder(itemView: View) : CardViewHolder(itemView) {

    val imageView: ImageView? = itemView.findViewById(R.id.imageView)
    val titleView: TextView? = itemView.findViewById(R.id.titleView)

}

class InviteCardViewHolder(itemView: View) : CardViewHolder(itemView) {

    val titleView: TextView? = itemView.findViewById(R.id.titleView)
    val descriptionView: TextView? = itemView.findViewById(R.id.descView)
}