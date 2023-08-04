package com.example.petshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.razorpay.Checkout

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")

        val itemsList: RecyclerView = findViewById(R.id.itemsList) // соеденяем с UI элементом
        val items = arrayListOf<Item>() // создаем массив из элементов ...

        items.add(Item(1, "dog", "Собаки", "Какое то описание", "Еще какое то описание", 100)) // объекты на основе отдельного класса Item
        items.add(Item(2, "cat", "Кошки", "Какое то описание", "Еще какое то описание", 100))
        items.add(Item(3, "bird", "Птицы" , "Какое то описание", "Еще какое то описание", 100))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)

    }
}