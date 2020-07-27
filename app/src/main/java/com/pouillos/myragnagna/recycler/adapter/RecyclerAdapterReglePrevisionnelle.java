package com.pouillos.myragnagna.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.ReglePrevisionnelle;
import com.pouillos.myragnagna.recycler.holder.RecyclerViewHolderReglePrevisionnelle;

import java.util.List;

public class RecyclerAdapterReglePrevisionnelle extends RecyclerView.Adapter<RecyclerViewHolderReglePrevisionnelle> {

        private List<ReglePrevisionnelle> listReglePrevisionnelle;

    public interface Listener {
        void onClickReglePrevisionnelleButton(int position);
    }

    private final Listener callback;


    public RecyclerAdapterReglePrevisionnelle(List<ReglePrevisionnelle> listReglePrevisionnelle, Listener callback) {
        this.listReglePrevisionnelle = listReglePrevisionnelle;
        this.callback = callback;
    }

        @Override
        public RecyclerViewHolderReglePrevisionnelle onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recycler_list_regle, parent, false);

            return new RecyclerViewHolderReglePrevisionnelle(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderReglePrevisionnelle viewHolder, int position) {
            viewHolder.updateWithReglePrevisionnelle(this.listReglePrevisionnelle.get(position),this.callback);
        }

        @Override
        public int getItemCount() {
            return this.listReglePrevisionnelle.size();
        }

    public ReglePrevisionnelle getReglePrevisionnelle(int position){
        return this.listReglePrevisionnelle.get(position);
    }

}
