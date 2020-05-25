package com.example.distribucionesmoya.model

import java.io.Serializable

data class Grupos(var img:String="", var id:Long=0,var tipo:String=""):Serializable
data class Productos(var descripcion:String="",var img:String="",var nombre:String="",var cantidad:String="",var precio:String="",var tipo:String="",var id:Long=0):Serializable
data class Usuarios(var Id:String="",var apellidos:String="",var email:String="",var nombre:String="",var usuario:String="")
data class Carrito(var IdProducto:Long=0,var Idusuario:String="",var Pproducto:String="",var Img:String="",var Total:String="",var Nomp:String="")
data class Pedidos(var IdProducto: Long=0,var Idusuario:String="",var Idpedido:String="",var Nomp:String="",var Prep:String="")