package com.example.distribucionesmoya.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.distribucionesmoya.R
import com.example.distribucionesmoya.adapter.CarAdapter
import com.example.distribucionesmoya.model.Carrito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_carrito.*

import kotlinx.android.synthetic.main.activity_carrito.*
import kotlinx.android.synthetic.main.content_carrito.*

class Carrito : AppCompatActivity() {
    companion object {
        const val TAG = "Distribuciones Moya"
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: CarAdapter
    private lateinit var db: FirebaseFirestore
    private var carro: ArrayList<Carrito> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        db = FirebaseFirestore.getInstance()
        initRV()
        setListener()
        auth= FirebaseAuth.getInstance()

    }



    private fun initRV() {
        adapter = CarAdapter(this, R.layout.row_car)
        rvCar.adapter = adapter
        rvCar.layoutManager = LinearLayoutManager(this)
    }

    private fun setListener() {
        val docRef = db.collection("Carrito")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                documentToList(snapshot.documents)
                adapter.setCarrito(carro)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun documentToList(documents: List<DocumentSnapshot>) {
        carro.clear()
        documents.forEach { d ->
            val IdProducto = 0L
            val Idusuario = d["Idusuario"] as String
            val Img = d["Img"] as String
            val Nomp = d["Nomp"] as String
            val Pproducto = d["Pproducto"] as String
            carro.add(Carrito(IdProducto = IdProducto,Idusuario = Idusuario, Img = Img,Nomp=Nomp,Pproducto = Pproducto))

        }
    }





}
