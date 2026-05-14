package com.example.shaalevikas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shaalevikas.adapters.NeedAdapter
import com.example.shaalevikas.models.Need

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var needList: ArrayList<Need>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add Need Button
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddNeedActivity::class.java))
        }

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Data List
        needList = ArrayList()

        // Item 1
        needList.add(
            Need(
                title = "Classroom Painting",
                cost = "5000",
                progress = 60,
                image = R.drawable.classroom
            )
        )

        // Item 2
        needList.add(
            Need(
                title = "New Benches",
                cost = "12000",
                progress = 40,
                image = R.drawable.benches
            )
        )

        // Item 3
        needList.add(
            Need(
                title = "Toilet Repair",
                cost = "8000",
                progress = 75,
                image = R.drawable.toilet
            )
        )

        // Adapter
        val adapter = NeedAdapter(needList)
        recyclerView.adapter = adapter
    }
}