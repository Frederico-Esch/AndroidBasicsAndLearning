package com.frederico.listaandcheckbox.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.frederico.listaandcheckbox.R;
import com.frederico.listaandcheckbox.models.GamesHandler;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {
    List<GamesHandler> GameList;
    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View GameList = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.games_list_card_1,
                parent,
                false);
        return new GamesViewHolder(GameList);
    }

    @Override
    public void onBindViewHolder(GamesAdapter.GamesViewHolder holder, int position) {
        String name = GameList.get(position).getName();
        String genre = GameList.get(position).getGenre();
        holder.Title.setText(name);
        holder.Genre.setText(genre);
        holder.CheckButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            GameList.get(position).getCheckButton().checked(name, genre, (isChecked) ? 1 : 0);
        });
    }

    @Override
    public int getItemCount() {
        return GameList.size();
    }

    public GamesAdapter(List<GamesHandler> gamelist){
        GameList = gamelist;
    }

    static class GamesViewHolder extends RecyclerView.ViewHolder{
        TextView Title;
        TextView Genre;
        ToggleButton CheckButton;
        public GamesViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.games_list_card_title);
            Genre = itemView.findViewById(R.id.games_list_card_genre);
            CheckButton = itemView.findViewById(R.id.games_list_card_toggle);
        }
    }
}
