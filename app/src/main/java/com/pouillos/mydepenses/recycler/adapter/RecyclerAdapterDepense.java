package com.pouillos.mydepenses.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.entities.Depense;
import com.pouillos.mydepenses.recycler.holder.RecyclerViewHolderDepense;

import java.util.List;

public class RecyclerAdapterDepense extends RecyclerView.Adapter<RecyclerViewHolderDepense> {

        private List<Depense> listDepense;

    public interface Listener {
        void onClickDepenseButton(int position);
    }

    private final Listener callback;


    public RecyclerAdapterDepense(List<Depense> listDepense, Listener callback) {
        this.listDepense = listDepense;
        this.callback = callback;
    }

        @Override
        public RecyclerViewHolderDepense onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recycler_list_depense, parent, false);

            return new RecyclerViewHolderDepense(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderDepense viewHolder, int position) {
            viewHolder.updateWithDepense(this.listDepense.get(position),this.callback);
        }

        @Override
        public int getItemCount() {
            return this.listDepense.size();
        }

    public Depense getDepense(int position){
        return this.listDepense.get(position);
    }

}
