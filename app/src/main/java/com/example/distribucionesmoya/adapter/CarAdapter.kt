package com.example.distribucionesmoya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.distribucionesmoya.model.Carrito
import com.example.distribucionesmoya.model.Productos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_car.view.*
import kotlinx.android.synthetic.main.row_cc.view.*

class CarAdapter (val context: Context,
                  val layout: Int
        ) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    private var dataList: List<Carrito> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCarrito(carr: List<Carrito>) {
        this.dataList = carr
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Carrito){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem

            itemView.tvNombreCar.text = dataItem.Nomp
            itemView.tvPrecioCar.text= dataItem.Pproducto


            Picasso.get().load(dataItem.Img).into(itemView.img_Car)


            //Log.e("TAG",dataItem.toString())
            itemView.tag = dataItem

        }

    }
}
