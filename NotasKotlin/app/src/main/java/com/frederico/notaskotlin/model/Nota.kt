package com.frederico.notaskotlin.model

data class Nota(
    val id: String,
    var texto:String = "",
    var titulo:String = "",
    val delete:( position: String ) -> Unit,
    val edit: (position: String ) -> Unit,
    var label:Int = 0,
)