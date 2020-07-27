package com.pouillos.myragnagna.activities.afficher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.NavDrawerActivity;
import com.pouillos.myragnagna.entities.BudgetMensuel;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterBudgetMensuel;
import com.pouillos.myragnagna.utils.ItemClickSupport;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;

public class AfficherListeBudgetMensuelActivity extends NavDrawerActivity implements RecyclerAdapterBudgetMensuel.Listener {





    private List<BudgetMensuel> listBudgetMensuel;
    private List<BudgetMensuel> listBudgetMensuelBD;

    private RecyclerAdapterBudgetMensuel adapter;

    @BindView(R.id.listeBudgetMensuel)
    RecyclerView listeBudgetMensuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_budget_mensuel);
        // 6 - Configure all views
      //  this.configureToolBar();
       // this.configureDrawerLayout();
      //  this.configureNavigationView();
        ButterKnife.bind(this);
      //  activeUser = findActiveUser();

       // traiterIntent();
        listBudgetMensuelBD = budgetMensuelDao.loadAll();

            // 6 - Configure all views
            this.configureToolBar();
            this.configureDrawerLayout();
            this.configureNavigationView();


        configureRecyclerView();
        configureOnClickRecyclerView();
    }

    public void configureRecyclerView() {
        adapter = new RecyclerAdapterBudgetMensuel(listBudgetMensuelBD,this);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        listeBudgetMensuel.setAdapter(adapter);
        // 3.4 - Set layout manager to position the items
        //this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listeBudgetMensuel.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(listeBudgetMensuel, R.layout.recycler_list_depense)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                    }
                });
    }

    @Override
    public void onClickBudgetMensuelButton(int position) {
        BudgetMensuel budgetMensuel = adapter.getBudgetMensuel(position);
        Toast.makeText(AfficherListeBudgetMensuelActivity.this, "a faire ", Toast.LENGTH_SHORT).show();
        //budgetMensuel.delete();
      //  ouvrirActiviteSuivante(AfficherListeBudgetMensuelActivity.this,AfficherBudgetMensuelActivity.class,"budgetMensuelId",budgetMensuel.getId(),true);

        //listBudgetMensuelBD.remove(position);
        //adapter.notifyItemRemoved(position);
    }
    


}
