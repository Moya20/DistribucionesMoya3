package com.example.distribucionesmoya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.distribucionesmoya.model.Productos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cc.view.*

class CcAdapter(val context: Context,
                val layout: Int
                ) : RecyclerView.Adapter<CcAdapter.ViewHolder>() {

        private var dataList: List<Productos> = emptyList()

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

        internal fun setProductos(productos: List<Productos>) {
            this.dataList = productos
            notifyDataSetChanged()
        }


        class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
            fun bind(dataItem: Productos){
                // itemview es el item de diseño
                // al que hay que poner los datos del objeto dataItem

                itemView.tvNombre.text = dataItem.nombre
                itemView.tvPrecio.text= dataItem.precio

               Picasso.get().load(dataItem.img).into(itemView.img_cc)


                //Log.e("TAG",dataItem.toString())
                itemView.tag = dataItem

            }

        }
    }
