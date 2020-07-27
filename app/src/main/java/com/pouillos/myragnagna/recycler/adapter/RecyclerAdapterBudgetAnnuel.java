package com.pouillos.myragnagna.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.BudgetAnnuel;
import com.pouillos.myragnagna.recycler.holder.RecyclerViewHolderBudgetAnnuel;

import java.util.List;

public class RecyclerAdapterBudgetAnnuel extends RecyclerView.Adapter<RecyclerViewHolderBudgetAnnuel> {

        private List<BudgetAnnuel> listBudgetAnnuel;

    public interface Listener {
        void onClickBudgetAnnuelButton(int position);
    }

    private final Listener callback;


    public RecyclerAdapterBudgetAnnuel(List<BudgetAnnuel> listBudgetAnnuel, Listener callback) {
        this.listBudgetAnnuel = listBudgetAnnuel;
        this.callback = callback;
    }

        @Override
        public RecyclerViewHolderBudgetAnnuel onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recycler_list_depense, parent, false);

            return new RecyclerViewHolderBudgetAnnuel(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderBudgetAnnuel viewHolder, int position) {
            viewHolder.updateWithBudgetAnnuel(this.listBudgetAnnuel.get(position),this.callback);
        }

        @Override
        public int getItemCount() {
            return this.listBudgetAnnuel.size();
        }

    public BudgetAnnuel getBudgetAnnuel(int position){
        return this.listBudgetAnnuel.get(position);
    }

}
