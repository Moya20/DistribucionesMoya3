package com.example.distribucionesmoya.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.distribucionesmoya.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class Register : AppCompatActivity() {

    private lateinit var txtName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtName=findViewById(R.id.txtName)
        txtLastName=findViewById(R.id.txtLastName)
        txtEmail=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtPassword)

        progressBar=findViewById(R.id.progressBar)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()


    }
    fun register (view: View){
        createNewAccount()
    }
    private fun createNewAccount(){
         val name:String=txtName.text.toString()
         val lastname:String=txtLastName.text.toString()
         val email:String=txtEmail.text.toString()
         val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(lastname)&&!TextUtils.isEmpty(email) &&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password) ){
            progressBar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
                task ->
                if (task.isComplete){
                    db = FirebaseFirestore.getInstance()
                    //guardo los datos de usuario
                    val user: MutableMap<String, Any> = HashMap()
                    user["usuario"] = txtEmail.text.toString()
                    user["Id"] = auth.currentUser!!.uid

                    db.collection("Usuarios")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                Login.TAG,
                                "DocummentSnapshot added with ID: "
                            )
                        }
                        .addOnFailureListener { e -> Log.w(Login.TAG, "Error adding document", e) }
                    val users: FirebaseUser? =auth.currentUser
                    verifyEmail(users)


                    action()




                }
            }
        }
    }
    private fun action(){
        startActivity(Intent(this,Login::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this) {
            task ->

            if(task.isComplete){
                Toast.makeText(this,"Email enviado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Error al enviar el email", Toast.LENGTH_LONG).show()
            }
        }
    }
}
