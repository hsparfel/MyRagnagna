package com.pouillos.myragnagna.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.BudgetMensuel;
import com.pouillos.myragnagna.recycler.holder.RecyclerViewHolderBudgetMensuel;

import java.util.List;

public class RecyclerAdapterBudgetMensuel extends RecyclerView.Adapter<RecyclerViewHolderBudgetMensuel> {

        private List<BudgetMensuel> listBudgetMensuel;

    public interface Listener {
        void onClickBudgetMensuelButton(int position);
    }

    private final Listener callback;


    public RecyclerAdapterBudgetMensuel(List<BudgetMensuel> listBudgetMensuel, Listener callback) {
        this.listBudgetMensuel = listBudgetMensuel;
        this.callback = callback;
    }

        @Override
        public RecyclerViewHolderBudgetMensuel onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recycler_list_depense, parent, false);

            return new RecyclerViewHolderBudgetMensuel(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolderBudgetMensuel viewHolder, int position) {
            viewHolder.updateWithBudgetMensuel(this.listBudgetMensuel.get(position),this.callback);
        }

        @Override
        public int getItemCount() {
            return this.listBudgetMensuel.size();
        }

    public BudgetMensuel getBudgetMensuel(int position){
        return this.listBudgetMensuel.get(position);
    }

}
