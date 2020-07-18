package com.pouillos.mydepenses.activities.afficher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.activities.NavDrawerActivity;
import com.pouillos.mydepenses.entities.BudgetAnnuel;
import com.pouillos.mydepenses.recycler.adapter.RecyclerAdapterBudgetAnnuel;
import com.pouillos.mydepenses.utils.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;

public class AfficherListeBudgetAnnuelActivity extends NavDrawerActivity implements RecyclerAdapterBudgetAnnuel.Listener {





    private List<BudgetAnnuel> listBudgetAnnuel;
    private List<BudgetAnnuel> listBudgetAnnuelBD;

    private RecyclerAdapterBudgetAnnuel adapter;

    @BindView(R.id.listeBudgetAnnuel)
    RecyclerView listeBudgetAnnuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_budget_annuel);
        // 6 - Configure all views
      //  this.configureToolBar();
       // this.configureDrawerLayout();
      //  this.configureNavigationView();
        ButterKnife.bind(this);
      //  activeUser = findActiveUser();

       // traiterIntent();
        listBudgetAnnuelBD = budgetAnnuelDao.loadAll();

            // 6 - Configure all views
            this.configureToolBar();
            this.configureDrawerLayout();
            this.configureNavigationView();


        configureRecyclerView();
        configureOnClickRecyclerView();
    }

    public void configureRecyclerView() {
        adapter = new RecyclerAdapterBudgetAnnuel(listBudgetAnnuelBD,this);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        listeBudgetAnnuel.setAdapter(adapter);
        // 3.4 - Set layout manager to position the items
        //this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listeBudgetAnnuel.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(listeBudgetAnnuel, R.layout.recycler_list_depense)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                    }
                });
    }

    @Override
    public void onClickBudgetAnnuelButton(int position) {
        BudgetAnnuel budgetAnnuel = adapter.getBudgetAnnuel(position);
        Toast.makeText(AfficherListeBudgetAnnuelActivity.this, "a faire ", Toast.LENGTH_SHORT).show();
        //budgetAnnuel.delete();
      //  ouvrirActiviteSuivante(AfficherListeBudgetAnnuelActivity.this,AfficherBudgetAnnuelActivity.class,"budgetAnnuelId",budgetAnnuel.getId(),true);

        //listBudgetAnnuelBD.remove(position);
        //adapter.notifyItemRemoved(position);
    }
    


}
