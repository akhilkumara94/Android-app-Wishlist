package com.akhil.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items : MutableList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = ArrayList<Item>()
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRv)
        val adapter = ItemAdapter(items)
        itemsRv.adapter = adapter
        itemsRv.layoutManager = LinearLayoutManager(this)


        val itemName : TextView = findViewById(R.id.itemName)
        val itemPrice : TextView = findViewById(R.id.itemPrice)
        val itemUrl : TextView = findViewById(R.id.itemURL)
        val submitButton : Button = findViewById(R.id.submitButton)

        submitButton.setOnClickListener{
            val item = Item(itemName.text.toString(), itemPrice.text.toString(), itemUrl.text.toString())
            items.add(item)
            adapter.notifyDataSetChanged()

            itemName.text = ""
            itemPrice.text = ""
            itemUrl.text = ""

        }
    }
}