package com.example.leakmemory.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leakmemory.R
import com.example.leakmemory.adapter.MyAdapter
import com.example.leakmemory.viewmodel.RecyclerViewViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: RecyclerViewViewModel by viewModels()
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter(emptyList(), this)
        recyclerView.adapter = adapter

        viewModel.liveDataOfList.observe(this) { data ->
            data?.let {
                adapter?.updateData(it)
            }
        }

        // Intentionally create a memory leak by keeping a static reference to the Activity
        viewModel.setActivity(this)

        viewModel.loadMoreData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
