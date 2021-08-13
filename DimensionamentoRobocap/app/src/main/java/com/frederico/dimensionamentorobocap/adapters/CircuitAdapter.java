package com.frederico.dimensionamentorobocap.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frederico.dimensionamentorobocap.R;
import com.frederico.dimensionamentorobocap.models.CircuitHandler;

import java.util.List;

import static java.util.Objects.isNull;

public class CircuitAdapter extends RecyclerView.Adapter<CircuitAdapter.CircuitHolder> {
    List<CircuitHandler> circuitHandlerList;

    public CircuitAdapter(List<CircuitHandler> handler){
        circuitHandlerList = handler;
    }

    @Override
    public CircuitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View CircuitView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_circuit_1,
                parent,
false);
        return new CircuitHolder(CircuitView);
    }

    @Override
    public void onBindViewHolder(CircuitAdapter.CircuitHolder holder, int position) {
        String name = circuitHandlerList.get(position).getName();
        int image_id = circuitHandlerList.get(position).getSrc_Image();
        holder.Name.setText(name);
        holder.Image.setImageResource(image_id);
        holder.Image.setMaxHeight(circuitHandlerList.get(position).getMax_Height());
        if(isNull(circuitHandlerList.get(position).getEnterCircuit())){
            holder.Image.setOnClickListener(v -> Toast.makeText(holder.itemView.getContext(), "Error", Toast.LENGTH_LONG).show());
        }else{
            holder.Image.setOnClickListener(v -> circuitHandlerList.get(position).getEnterCircuit().Click(name, image_id));
        }
    }

    @Override
    public int getItemCount() {
        return circuitHandlerList.size();
    }

    public class CircuitHolder extends RecyclerView.ViewHolder{
        TextView Name;
        ImageButton Image;
        public CircuitHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.card_circuit_name);
            Image = itemView.findViewById(R.id.card_circuit_image);
        }
    }
}
