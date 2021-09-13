package com.frederico.firebasefirst.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frederico.firebasefirst.R;
import com.frederico.firebasefirst.models.TarefaModel;

import java.util.List;
import java.util.Locale;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    List<TarefaModel> lista;

    public TarefaAdapter(List<TarefaModel> list){
        lista = list;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tarefaView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tarefa_layout,
                parent,
                false);
        return new TarefaViewHolder(tarefaView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        String content = lista.get(position).getContent();
        String title = lista.get(position).getTitle();
        String id = lista.get(position).getId();

        holder.title.setText(String.format("%s:", title));
        holder.content.setText(content);
        holder.background.setOnClickListener(v->{
            lista.get(position).getPress().Short(id);
        });
        holder.background.setOnLongClickListener(v->{
            lista.get(position).getPress().Long(id);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        LinearLayout background;
        public TarefaViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title_holder);
            content = itemView.findViewById(R.id.content_holder);
            background = itemView.findViewById(R.id.linear_layout);
        }
    }
}
