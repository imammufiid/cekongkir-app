package com.mufiid.quizwsp.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mufiid.quizwsp.R
import com.mufiid.quizwsp.models.Cost
import kotlinx.android.synthetic.main.item_courier.view.*

class CostAdapter : RecyclerView.Adapter<CostAdapter.ViewHolder>() {

    private val data = ArrayList<Cost>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: Cost) {
            itemView.service.text = itemView.resources.getString(R.string.service, data.service)
            itemView.estimasi.text = itemView.resources.getString(R.string.estimasi, data.cost?.get(0)?.etd)
            itemView.price.text = itemView.resources.getString(R.string.harga, data.cost?.get(0)?.value.toString())
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_courier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CostAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setCost(user: List<Cost>) {
        data.clear()
        data.addAll(user)
        notifyDataSetChanged()
    }
}