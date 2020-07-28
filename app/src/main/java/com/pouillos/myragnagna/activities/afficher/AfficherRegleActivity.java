package com.pouillos.myragnagna.activities.afficher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.AccueilActivity;
import com.pouillos.myragnagna.activities.NavDrawerActivity;
import com.pouillos.myragnagna.entities.Regle;
import com.pouillos.myragnagna.utils.DateUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AfficherRegleActivity extends NavDrawerActivity {
    
    @BindView(R.id.textDate)
    TextInputEditText textDate;
    @BindView(R.id.layoutDate)
    TextInputLayout layoutDate;
    Date date;

    @BindView(R.id.fabSave)
    FloatingActionButton fabSave;
    @BindView(R.id.fabDelete)
    FloatingActionButton fabDelete;

    Regle currentRegle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_regle);

        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        textDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog(view, textDate,false,false,null,null);
                    textDate.clearFocus();
                }
            }
        });
        date = new Date();
        textDate.setText(DateUtils.ecrireDate(date));
        fabDelete.hide();
        traiterIntent();
    }

    private void traiterIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("regleId")) {
            fabDelete.show();
            Long regleId = intent.getLongExtra("regleId", 0);
            currentRegle = regleDao.load(regleId);
            textDate.setText(DateUtils.ecrireDate(currentRegle.getDate()));
            date = currentRegle.getDate();
        }
    }

    @OnClick(R.id.fabSave)
    public void setfabSaveClick() {
        if (isFullRempli()) {

            List<Regle> listRegles = regleDao.loadAll();
            Collections.sort(listRegles);
            Regle lastRegle = listRegles.get(0);

            Regle regle = new Regle();
            if (currentRegle != null) {
                regle = currentRegle;
                lastRegle = trouverReglePrecedente(currentRegle);
            }
            //lastRegle = trouverReglePrecedente(regle);
                date = convertStringToDate(textDate.getText().toString());
                regle.setDate(date);
                regle.setDateString(DateUtils.ecrireDate(date));

                Double delta = DateUtils.getDaysBetweenDates(lastRegle.getDate(),regle.getDate());
                regle.setIntervalle((int) Math.round(delta));
                if (currentRegle != null) {
                    regleDao.update(regle);
                } else {
                    regleDao.insert(regle);
                }
                recalculerTousIntervalles();
            ouvrirActiviteSuivante(AfficherRegleActivity.this, AccueilActivity.class,false);
    }
    }

    @OnClick(R.id.fabDelete)
    public void setfabDeleteClick() {
        regleDao.delete(currentRegle);
        recalculerTousIntervalles();
        ouvrirActiviteSuivante(AfficherRegleActivity.this, AccueilActivity.class,false);
    }

    private boolean isFullRempli() {
        boolean bool = true;
        if (!isFilled(textDate)) {
            textDate.setError("Obligatoire");
            bool = false;
        }
        return bool;
    }

}
