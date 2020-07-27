package com.pouillos.myragnagna.activities.afficher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.NavDrawerActivity;
import com.pouillos.myragnagna.entities.Regle;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterRegle;
import com.pouillos.myragnagna.utils.ItemClickSupport;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AfficherListeRegleActivity extends NavDrawerActivity implements RecyclerAdapterRegle.Listener {

    private List<Regle> listRegleBD;

    private RecyclerAdapterRegle adapter;

    @BindView(R.id.listeRegle)
    RecyclerView listeRegle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_liste_regle);

        ButterKnife.bind(this);

        listRegleBD = regleDao.loadAll();
        Collections.sort(listRegleBD);

            this.configureToolBar();
            this.configureDrawerLayout();
            this.configureNavigationView();
        configureRecyclerView();
        configureOnClickRecyclerView();
    }

    public void configureRecyclerView() {
        adapter = new RecyclerAdapterRegle(listRegleBD,this);
        listeRegle.setAdapter(adapter);
        listeRegle.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(listeRegle, R.layout.recycler_list_regle)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Log.e("TAG", "Position : "+position);
                    }
                });
    }

    @Override
    public void onClickRegleButton(int position) {
        Regle regle = adapter.getRegle(position);
        Toast.makeText(AfficherListeRegleActivity.this, "a faire click regle", Toast.LENGTH_SHORT).show();
        ouvrirActiviteSuivante(AfficherListeRegleActivity.this,AfficherRegleActivity.class,"regleId",regle.getId(),true);
    }
}
