package com.pouillos.myragnagna.recycler.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.BudgetAnnuel;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterBudgetAnnuel;
import com.pouillos.myragnagna.utils.DateUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolderBudgetAnnuel extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.depense)
    TextView budgetAnnuel;


    private WeakReference<RecyclerAdapterBudgetAnnuel.Listener> callbackWeakRef;

    public RecyclerViewHolderBudgetAnnuel(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //ButterKnife.bind(this);
    }

    public void updateWithBudgetAnnuel(BudgetAnnuel budgetAnnuel, RecyclerAdapterBudgetAnnuel.Listener callback){
       // double distance = ChercherContactActivity.calculerDistance(latitude,longitude,contact.getLatitude(),contact.getLongitude());

//todo revoir l'affichage en limitan apres la virgule - FAIT OK
       // NumberFormat formatter = new DecimalFormat("#0.0");
       // System.out.println(formatter.format(4.0));

      //  this.detail.setText(contact.getRaisonSocial() + " - " + formatter.format(distance)+ " km");
        this.budgetAnnuel.setText(budgetAnnuel.getMontant() + " € - " + DateUtils.recupAnnee(budgetAnnuel.getDate()));
        this.budgetAnnuel.setOnClickListener(this);
        //4 - Create a new weak Reference to our Listener
        this.callbackWeakRef = new WeakReference<RecyclerAdapterBudgetAnnuel.Listener>(callback);
    }


    @Override
    public void onClick(View view) {
        // 5 - When a click happens, we fire our listener.
        RecyclerAdapterBudgetAnnuel.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickBudgetAnnuelButton(getAdapterPosition());
    }
}
