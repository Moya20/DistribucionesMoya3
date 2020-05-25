package com.example.distribucionesmoya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.distribucionesmoya.model.Grupos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*


class CustomAdapter(val context: Context,
                    val layout: Int
                     ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        private var dataList: List<Grupos> = emptyList()

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

        internal fun setGrupos(grupos: List<Grupos>) {
            this.dataList = grupos
            notifyDataSetChanged()
        }


        class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
            fun bind(dataItem: Grupos){
                // itemview es el item de diseño
                // al que hay que poner los datos del objeto dataItem
                itemView.tvTipo.text = dataItem.tipo

                Picasso.get().load(dataItem.img).into(itemView.imageView)

                itemView.tag = dataItem

            }

        }
    }
