package com.frederico.notaskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import com.frederico.notaskotlin.model.Nota
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text

class EditNote : AppCompatActivity() {
    var ID:String = ""
    var Text:String = ""
    var Titulo:String = ""
    var Label:Int = 0
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val labelInput = findViewById<RadioGroup>(R.id.labelChoice)
        labelInput.check(R.id.black)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        if(intent.getStringExtra("ID") != null){
            ID = intent.getStringExtra("ID")!!
            toolbar.title = "Editar Nota"
            val tituloInput = findViewById<TextInputLayout>(R.id.input_titulo)
            val textInput = findViewById<TextInputLayout>(R.id.input_texto)

            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(snap in snapshot.children){
                        if(snap.key.toString() == ID){
                            Titulo = snap.child("titulo").value.toString()
                            Text = snap.child("texto").value.toString()
                            Label = snap.child("label").value.toString().toInt()
                            textInput.editText!!.setText(Text)
                            tituloInput.editText!!.setText(Titulo)
                            labelInput.check(when(Label) {
                                0 -> R.id.black
                                1 -> R.id.red
                                2 -> R.id.blue
                                3 -> R.id.green
                                4 -> R.id.purple
                                else -> R.id.black
                            })
                            break
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })



        }else{
            toolbar.title = "Nova nota"
        }
        println(ID)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editar,  menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.salvar) {
            val tituloInput = findViewById<TextInputLayout>(R.id.input_titulo)
            val textInput = findViewById<TextInputLayout>(R.id.input_texto)
            val labelInput = findViewById<RadioGroup>(R.id.labelChoice)

            val titulo = tituloInput.editText!!.text.toString()
            val texto = textInput.editText!!.text.toString()
            val label = when (labelInput.checkedRadioButtonId) {
                R.id.black -> 0
                R.id.red -> 1
                R.id.blue -> 2
                R.id.green -> 3
                R.id.purple -> 4
                else -> 0
            }

            if (ID == "") {
                ID = database.push().key.toString()
                database.child(ID).child("titulo").setValue(titulo)
                database.child(ID).child("texto").setValue(texto)
                database.child(ID).child("label").setValue(label)
            }else{
                database.child(ID).child("texto").setValue(texto)
                database.child(ID).child("titulo").setValue(titulo)
                database.child(ID).child("label").setValue(label)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}