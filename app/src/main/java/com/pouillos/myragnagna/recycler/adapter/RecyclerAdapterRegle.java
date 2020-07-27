package com.pouillos.myragnagna.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.Regle;
import com.pouillos.myragnagna.recycler.holder.RecyclerViewHolderRegle;

import java.util.List;

public class RecyclerAdapterRegle extends RecyclerView.Adapter<RecyclerViewHolderRegle> {

        private List<Regle> listRegle;

    public interface Listener {
        void onClickRegleButton(int position);
    }

    private final Listener callback;


    public RecyclerAdapterRegle(List<Regle> listRegle, Listener callback) {
        this.listRegle = listRegle;
        this.callback = callback;
    }

        @Override
        public RecyclerViewHolderRegle onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recycler_list_regle, parent, false);

            return new RecyclerViewHolderRegle(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderRegle viewHolder, int position) {
            viewHolder.updateWithRegle(this.listRegle.get(position),this.callback);
        }

        @Override
        public int getItemCount() {
            return this.listRegle.size();
        }

    public Regle getRegle(int position){
        return this.listRegle.get(position);
    }

}
