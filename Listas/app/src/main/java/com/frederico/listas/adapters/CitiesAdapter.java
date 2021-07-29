package com.frederico.listas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.frederico.listas.R;
import com.frederico.listas.models.CitiesHandler;

import org.w3c.dom.Text;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    List<CitiesHandler> list;
    @Override
    public CitiesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View CitiesLista = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_list_card_1,
                parent,
                false);
        return new CitiesViewHolder(CitiesLista);
    }

    @Override
    public void onBindViewHolder(CitiesAdapter.CitiesViewHolder holder, int position) {
        holder.Name.setText(list.get(position).getName());
        holder.Country.setText(list.get(position).getCountry());
        holder.But.setOnClickListener(v -> {
            list.get(position).getFunc().butClic(list.get(position).getName(), list.get(position).getCountry());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public CitiesAdapter(List<CitiesHandler> list){
        this.list =list;
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Country;
        AppCompatButton But;
        public CitiesViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name_City);
            Country = itemView.findViewById(R.id.Country_City);
            But = itemView.findViewById(R.id.But_City);
        }
    }
}
