package com.example.leakmemory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private var data: List<MyData>,
    private val context: Context, // Giữ tham chiếu tới Context sẽ gây ra memory leak
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val tenTruyen: TextView = itemView.findViewById(R.id.tenTruyen)
        val tacGia: TextView = itemView.findViewById(R.id.tacGia)
        val soChuong: TextView = itemView.findViewById(R.id.soChuong)
        val theLoaiContainer: LinearLayout = itemView.findViewById(R.id.theLoaiContainer)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_design, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int,
    ) {
        val item = data[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.tenTruyen.text = item.title
        holder.tacGia.text = item.author
        holder.soChuong.text = "${item.chapterCount} chương"

        holder.theLoaiContainer.removeAllViews()
        item.genres.forEach { genre ->
            val textView = TextView(holder.itemView.context)
            textView.text = genre
            textView.setPadding(8, 4, 8, 4)
            textView.background = holder.itemView.context.getDrawable(R.drawable.bg_genre)
            holder.theLoaiContainer.addView(textView)
        }
    }

    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<MyData>) {
        data = newData
        notifyDataSetChanged()
    }
}
