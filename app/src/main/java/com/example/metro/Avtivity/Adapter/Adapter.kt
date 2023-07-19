package com.example.metro.Avtivity.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.metro.Avtivity.DataBase.Metro
import com.example.metro.Avtivity.DataBase.MetroDatabase
import com.example.metro.R

class Adapter(val metro: MutableList<Metro>) : RecyclerView.Adapter<Adapter.MetroViewHolder>(){

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetroViewHolder {
        return MetroViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent,false))
    }

    override fun getItemCount() = metro.size


    fun deleteItem(position: Int) {
        if (position >= 0 && position < metro.size) {
            // Get the item that needs to be deleted
            val deletedItem = metro[position]
            // Remove the item from the data set
            metro.removeAt(position)
            // Notify the adapter that an item has been removed
            notifyItemRemoved(position)
            val database: MetroDatabase = MetroDatabase.getDatabase(context)
            database.metroDao().delete(deletedItem)

       /* metro.removeAt(position)
        notifyItemRemoved(position)
        val database: MetroDatabase = MetroDatabase.getDatabase(context)
        val dao = database.metroDao()
        dao.delete(positionx)
            val database: MetroDatabase = MetroDatabase.getDatabase(context)
        database.metroDao().delete(metro[position])*/
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MetroViewHolder, position: Int) {
        holder.texViewName.text = "1-Number of Stations is : ${metro[position].count.toString()}"
        holder.textViewTrip.text = "2-Time of trip is : ${metro[position].timeOfTrip.toString()}"
        holder.textViewPrice.text = "3-Price of your ticket is : ${metro[position].ticketPrice.toString()}"
        holder.textViewStations.text = "4-Your Stations are : ${metro[position].routs}"
        context = holder.itemView.context

        holder.delete.setOnClickListener {
            deleteItem(position)
        }



    }

    class  MetroViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        val texViewName : TextView = view.findViewById(R.id.text_view_name)
        val textViewTrip : TextView = view.findViewById(R.id.text_view_trip)
        val textViewPrice : TextView = view.findViewById(R.id.text_view_price)
        val textViewStations : TextView = view.findViewById(R.id.text_view_stations)
        val delete : ImageButton = view.findViewById(R.id.delete)



    }


}

