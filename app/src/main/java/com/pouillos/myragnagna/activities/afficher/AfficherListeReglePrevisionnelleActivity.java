package com.pouillos.myragnagna.activities.afficher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.NavDrawerActivity;
import com.pouillos.myragnagna.entities.ReglePrevisionnelle;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterReglePrevisionnelle;
import com.pouillos.myragnagna.utils.ItemClickSupport;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AfficherListeReglePrevisionnelleActivity extends NavDrawerActivity implements RecyclerAdapterReglePrevisionnelle.Listener {

    private List<ReglePrevisionnelle> listReglePrevisionnelleBD;

    private RecyclerAdapterReglePrevisionnelle adapter;

    @BindView(R.id.listePrevision)
    RecyclerView listeReglePrevisionnelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_regle_previsionnelle);
        ButterKnife.bind(this);

        listReglePrevisionnelleBD = reglePrevisionnelleDao.loadAll();
        Collections.sort(listReglePrevisionnelleBD);

            this.configureToolBar();
            this.configureDrawerLayout();
            this.configureNavigationView();
        configureRecyclerView();
        configureOnClickRecyclerView();
    }

    public void configureRecyclerView() {
        adapter = new RecyclerAdapterReglePrevisionnelle(listReglePrevisionnelleBD,this);
        listeReglePrevisionnelle.setAdapter(adapter);
        listeReglePrevisionnelle.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(listeReglePrevisionnelle, R.layout.recycler_list_regle)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                    }
                });
    }

    @Override
    public void onClickReglePrevisionnelleButton(int position) {
        //ReglePrevisionnelle reglePrevisionnelle = adapter.getReglePrevisionnelle(position);
        //Toast.makeText(AfficherListeReglePrevisionnelleActivity.this, "a faire click reglePrevisionnelle", Toast.LENGTH_SHORT).show();
    }
}
