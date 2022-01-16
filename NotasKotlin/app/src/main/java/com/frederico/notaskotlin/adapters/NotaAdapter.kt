package com.frederico.notaskotlin.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.frederico.notaskotlin.R
import com.frederico.notaskotlin.model.Nota

class NotaAdapter (val notas: MutableList<Nota>) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.bind(notas[position], position)
    }

    override fun getItemCount(): Int = notas.size


    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(nota: Nota, position: Int){
            val titulo = itemView.findViewById<TextView>(R.id.Titulo_nota)
            val label  = itemView.findViewById<ImageView>(R.id.lable_flag)
            val texto = itemView.findViewById<TextView>(R.id.display_text_excert)

            titulo.text = nota.titulo
            texto.text = nota.texto
            when(nota.label){
                0 -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  R.color.black)))
                1 -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  android.R.color.holo_red_dark)))
                2 -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  android.R.color.holo_blue_dark)))
                3 -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  android.R.color.holo_green_dark)))
                4 -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  android.R.color.holo_purple)))
                else -> ImageViewCompat.setImageTintList(label, ColorStateList.valueOf(ContextCompat.getColor(itemView.context,  R.color.black)))
            }

            val deleteButton = itemView.findViewById<ImageButton>(R.id.botao_deletar)
            deleteButton.setOnClickListener { nota.delete(nota.id) }

            val editButton = itemView.findViewById<LinearLayout>(R.id.edit)
            editButton.setOnClickListener { nota.edit(nota.id) }
        }
    }
}