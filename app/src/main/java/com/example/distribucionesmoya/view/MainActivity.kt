package com.example.distribucionesmoya.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.distribucionesmoya.R
import android.content.Intent

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.distribucionesmoya.adapter.CustomAdapter
import com.example.distribucionesmoya.model.Grupos
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {//SearchView.OnQueryTextListener
    //private lateinit var searchView: SearchView
    companion object {
        const val TAG = "Distribuciones Moya"
    }
    private lateinit var adapter: CustomAdapter
    private lateinit var db: FirebaseFirestore
    private var grupos: ArrayList<Grupos> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        db= FirebaseFirestore.getInstance()
        initRV()
        setListener()

        fab.setOnClickListener { action() }
    }
    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.row)
        rvGrupos.adapter = adapter
        rvGrupos.layoutManager = LinearLayoutManager(this)
    }
    private fun setListener() {
        val docRef = db.collection("Grupos")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                documentToList(snapshot.documents)
                adapter.setGrupos(grupos)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }
    private fun documentToList(documents: List<DocumentSnapshot>) {
        grupos.clear()
        documents.forEach { d ->
            val img = d["Img"] as String
            val tipo = d["Tipo"] as String
            val id = d["Id"] as Long

            grupos.add(Grupos(img = img,tipo = tipo, id = id ))
        }
    }

    fun onclick(view: View) {

        val grupo = view.tag as Grupos
        val intent = Intent(this, ProductosActivity::class.java)
        intent.putExtra("Grupos", grupo)
        startActivity(intent)
    }

    private fun action() {
        startActivity(Intent(this, Carrito::class.java))
    }


}


