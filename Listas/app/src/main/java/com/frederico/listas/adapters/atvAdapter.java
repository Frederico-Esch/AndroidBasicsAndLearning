package com.frederico.listas.adapters;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frederico.listas.R;
import com.frederico.listas.models.atvModel;

import java.util.List;
import java.util.zip.Inflater;

public class atvAdapter extends RecyclerView.Adapter<atvAdapter.atvViewHolder> {
    List<atvModel> atvModelList;

    public atvAdapter(List<atvModel> list){ atvModelList = list;}

    @Override
    public atvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View atvView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.atv_list_layout,
                parent,
                false);

        return new atvViewHolder(atvView);
    }

    @Override
    public void onBindViewHolder(@NonNull atvViewHolder holder, int position) {
        String texto = atvModelList.get(position).getText();
        int Id = atvModelList.get(position).getId();
        holder.preview.setText(texto);

        holder.layout.setOnClickListener(v->{
            atvModelList.get(position).getPress().Short(Id);
        });
        holder.layout.setOnLongClickListener(view -> {
            atvModelList.get(position).getPress().Long(Id);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return atvModelList.size();
    }

    class atvViewHolder extends RecyclerView.ViewHolder{
        TextView preview;
        LinearLayout layout;
        public atvViewHolder(View itemView){
            super(itemView);
            preview = itemView.findViewById(R.id.preview);
            layout = itemView.findViewById(R.id.layout);
        }

    }
}
