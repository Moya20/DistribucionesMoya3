package com.example.distribucionesmoya.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.distribucionesmoya.R
import com.example.distribucionesmoya.adapter.CcAdapter
import com.example.distribucionesmoya.model.Grupos
import com.example.distribucionesmoya.model.Productos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_productos.*
import kotlinx.android.synthetic.main.content_productos.*

class ProductosActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Distribuciones Moya"
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var grupo: Grupos
    private lateinit var producto: Productos
    private lateinit var adapter: CcAdapter
    private lateinit var db: FirebaseFirestore
    private var productos: ArrayList<Productos> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
        db= FirebaseFirestore.getInstance()
        grupo = intent.getSerializableExtra("Grupos") as Grupos
        initRV()
        setListener()
        auth= FirebaseAuth.getInstance()

    }
    private fun initRV() {
        adapter = CcAdapter(this, R.layout.row_cc)
        rvCc.adapter = adapter
        rvCc.layoutManager = LinearLayoutManager(this)
    }
    private fun setListener() {

        val docRef = db.collection("Grupos").document("${grupo.id}").collection("Productos")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                documentToList(snapshot.documents)
                adapter.setProductos(productos)
            } else {
                Log.d(TAG, "Current data: null")

            }
        }
    }
    private fun documentToList(documents: List<DocumentSnapshot>) {
        productos.clear()
        documents.forEach { d ->
            val img = d["Img"] as String
            val descripcion = d["Descripcion"] as String
            val nombre = d["Nombre"] as String
            val cantidad = d["Cantidad"] as String
            val precio = d["Precio"] as String
            val id = d["Id"] as Long

            productos.add(Productos(img = img,descripcion = descripcion,nombre = nombre,cantidad = cantidad,precio = precio,id =id  ))
        }
    }


    fun onclickCarro(view: View) {
        val productoseleccionado = view.tag as Productos

        db = FirebaseFirestore.getInstance()
        val carro: MutableMap<String,Any> = HashMap()
        carro["Idusuario"] = auth.currentUser!!.uid
        carro["IdProducto"] = productoseleccionado.id
        carro["Pproducto"] = productoseleccionado.precio
       carro["Img"] = productoseleccionado.img
       carro["Nomp"] = productoseleccionado.nombre


        db.collection("Carrito")
            .add(carro)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    Login.TAG,
                    "DocummentSnapshot added with ID: "
                )

            }
            .addOnFailureListener { e -> Log.w(Login.TAG, "Error adding document", e) }

    }
}

