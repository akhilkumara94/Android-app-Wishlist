package com.akhil.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private  val items: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTV : TextView
        val itemPriceTV : TextView
        val itemUrlTV : TextView

        init {
            itemNameTV = itemView.findViewById(R.id.itemNameTV)
            itemPriceTV = itemView.findViewById(R.id.itemPriceTV)
            itemUrlTV = itemView.findViewById(R.id.itemURLTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.price_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.itemNameTV.text = item.itemName
        holder.itemPriceTV.text = item.itemPrice
        holder.itemUrlTV.text = item.itemUrl
        holder.itemView.setOnLongClickListener {
            items.removeAt(position)
            this.notifyDataSetChanged()
            true
        }

        holder.itemView.setOnClickListener{
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.itemUrl))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.itemName, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}