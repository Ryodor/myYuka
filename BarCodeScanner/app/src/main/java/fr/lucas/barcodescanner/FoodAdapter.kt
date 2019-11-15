package fr.lucas.barcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val itemScaned: FoodFacts)
    : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scaned, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameView.text
        holder.imgView.urls
        //  holder.imgMovieIdView.text = movie.imgMovieId
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NameView = itemView.findViewById<TextView>(R.id.Name)
        val imgView = itemView.findViewById<TextView>(R.id.img)
        // val imgMovieId = itemView.findViewById<imageView>(R.id.imgMovieId)
    }


}
