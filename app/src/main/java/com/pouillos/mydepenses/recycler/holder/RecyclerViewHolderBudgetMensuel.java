package com.pouillos.mydepenses.recycler.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.entities.BudgetMensuel;
import com.pouillos.mydepenses.recycler.adapter.RecyclerAdapterBudgetMensuel;
import com.pouillos.mydepenses.utils.DateUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolderBudgetMensuel extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.depense)
    TextView budgetMensuel;


    private WeakReference<RecyclerAdapterBudgetMensuel.Listener> callbackWeakRef;

    public RecyclerViewHolderBudgetMensuel(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //ButterKnife.bind(this);
    }

    public void updateWithBudgetMensuel(BudgetMensuel budgetMensuel, RecyclerAdapterBudgetMensuel.Listener callback){
       // double distance = ChercherContactActivity.calculerDistance(latitude,longitude,contact.getLatitude(),contact.getLongitude());

//todo revoir l'affichage en limitan apres la virgule - FAIT OK
       // NumberFormat formatter = new DecimalFormat("#0.0");
       // System.out.println(formatter.format(4.0));

      //  this.detail.setText(contact.getRaisonSocial() + " - " + formatter.format(distance)+ " km");
        this.budgetMensuel.setText(budgetMensuel.getMontant() + " € - " + DateUtils.ecrireMoisAnneeLettre(budgetMensuel.getDate()));
        this.budgetMensuel.setOnClickListener(this);
        //4 - Create a new weak Reference to our Listener
        this.callbackWeakRef = new WeakReference<RecyclerAdapterBudgetMensuel.Listener>(callback);
    }


    @Override
    public void onClick(View view) {
        // 5 - When a click happens, we fire our listener.
        RecyclerAdapterBudgetMensuel.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickBudgetMensuelButton(getAdapterPosition());
    }
}
