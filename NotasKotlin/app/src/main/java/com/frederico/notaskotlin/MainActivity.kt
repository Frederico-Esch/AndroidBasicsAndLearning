package com.frederico.notaskotlin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frederico.notaskotlin.adapters.NotaAdapter
import com.frederico.notaskotlin.model.Nota
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    val notas = mutableListOf<Nota>()
    lateinit var recyclerView: RecyclerView
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val adicionarNota = findViewById<ExtendedFloatingActionButton>(R.id.botao_nova_nota)

        getNotas()
        recyclerConfig(adicionarNota)

        adicionarNota.setOnClickListener {
            /*
            database.push().setValue(
                hashMapOf(
                    "titulo" to "titulo de dentro",
                    "texto" to "texto de dentro",
                    "label" to 0
                )
            )
            */
            val intent = Intent(this, EditNote::class.java) //.putExtra("ID", "TESTE")
            startActivity(intent)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun deletar(id: String){
        val nota = notas.find { it.id == id }
        database.child(nota!!.id).removeValue()
    }

    fun editar(id : String){
        val intent = Intent(this, EditNote::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    private fun getNotas(){
        database.addValueEventListener(object: ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snap in snapshot.children){
                    println(snap)
                    if(snap.hasChild("titulo") and snap.hasChild("texto") and snap.hasChild("label") ) {
                        if (notas.find { it.id == snap.key.toString() } != null) {
                            notas.find { it.id == snap.key.toString() }?.titulo =
                                snap.child("titulo").value.toString()
                            notas.find { it.id == snap.key.toString() }?.texto =
                                snap.child("texto").value.toString()
                            notas.find { it.id == snap.key.toString() }?.label =
                                snap.child("label").value.toString().toInt()
                        } else {
                            notas.add(
                                Nota(
                                    snap.key.toString(),
                                    snap.child("texto").value.toString(),
                                    snap.child("titulo").value.toString(),
                                    ::deletar,
                                    ::editar,
                                    snap.child("label").value.toString().toInt()
                                )
                            )
                        }
                    }
                }

                val databaseids = snapshot.children.map { it.key.toString() }.toSet()
                notas.removeAll(notas.filter { it.id !in databaseids })
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun recyclerConfig(botao: ExtendedFloatingActionButton){
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = NotaAdapter(notas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnScrollListener( object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                println(dy)
                if(dy > 0){
                    botao.hide()
                }else if(dy < -9 || dy == 0){
                    botao.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        recyclerView.setHasFixedSize(true)
    }
}