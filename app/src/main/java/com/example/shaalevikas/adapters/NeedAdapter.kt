package com.example.shaalevikas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shaalevikas.R
import com.example.shaalevikas.models.Need

class NeedAdapter(private val needList: ArrayList<Need>) :
    RecyclerView.Adapter<NeedAdapter.NeedViewHolder>() {

    class NeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.imgNeed)
        val title = itemView.findViewById<TextView>(R.id.txtTitle)
        val cost = itemView.findViewById<TextView>(R.id.txtCost)
        val progress = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val pledge = itemView.findViewById<Button>(R.id.btnPledge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_need, parent, false)

        return NeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NeedViewHolder, position: Int) {

        val need = needList[position]

        holder.title.text = need.title
        holder.cost.text = "Estimated Cost: ₹${need.cost}"
        holder.progress.progress = need.progress!!

        holder.image.setImageResource(need.image!!)

        holder.pledge.setOnClickListener {

            var currentProgress = need.progress ?: 0

            if (currentProgress < 100) {

                currentProgress += 10

                need.progress = currentProgress

                holder.progress.progress = currentProgress

                if (currentProgress == 100) {

                    Toast.makeText(
                        holder.itemView.context,
                        "Project Completed Successfully 🎉",
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    Toast.makeText(
                        holder.itemView.context,
                        "Support Pledged Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return needList.size
    }
}