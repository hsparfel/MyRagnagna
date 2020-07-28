package com.pouillos.myragnagna.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.afficher.AfficherRegleActivity;
import com.pouillos.myragnagna.entities.Regle;
import com.pouillos.myragnagna.entities.ReglePrevisionnelle;
import com.pouillos.myragnagna.utils.DateUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AccueilActivity extends NavDrawerActivity {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.fabAddRegle)
    FloatingActionButton fabAddRegle;

    @BindView(R.id.my_progressBar)
    ProgressBar progressBar;

    @BindView(R.id.textDate)
    TextInputEditText textDate;
    @BindView(R.id.layoutDate)
    TextInputLayout layoutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Stetho.initializeWithDefaults(this);

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        ButterKnife.bind(this);

        textView.setText(DateUtils.ecrireDateLettre(new Date()));

        progressBar.setVisibility(View.VISIBLE);
        AccueilActivity.AsyncTaskRunnerBD runnerBD = new AccueilActivity.AsyncTaskRunnerBD();
        runnerBD.execute();

    }

    private int calculerMoyenneIntervalle() {
        int moyenne;
        Float moyenneFloat = 0f;
        List<Regle> listRegles = regleDao.loadAll();
        for (Regle currentRegle : listRegles) {
            moyenneFloat += currentRegle.getIntervalle();
        }
        moyenne = Math.round(moyenneFloat/(listRegles.size()-1));
        return moyenne;
    }

    @OnClick(R.id.fabAddRegle)
    public void setFabAddRegleClick() {
        ouvrirActiviteSuivante(this, AfficherRegleActivity.class,false);
    }

    private class AsyncTaskRunnerBD extends AsyncTask<Void, Integer, Void> {
        ReglePrevisionnelle nextPrevision;
        protected Void doInBackground(Void...voids) {
            publishProgress(0);
            publishProgress(10);
            remplirBDDefaut();
            publishProgress(60);
            List<Regle> listRegles = regleDao.loadAll();
            Collections.sort(listRegles);
            Regle lastRegle = listRegles.get(0);
            int moyenne = calculerMoyenneIntervalle();
            reglePrevisionnelleDao.deleteAll();
            for (int i=1; i<21 ;i++) {
                ReglePrevisionnelle reglePrevisionnelle = new ReglePrevisionnelle();
                Date date = DateUtils.ajouterJourArrondi(lastRegle.getDate(),moyenne,0);
                reglePrevisionnelle.setDate(date);
                reglePrevisionnelle.setDateString(DateUtils.ecrireDate(date));
                reglePrevisionnelleDao.insert(reglePrevisionnelle);
                lastRegle.setDate(date);
                lastRegle.setDateString(DateUtils.ecrireDateHeure(date));
            }
            List<ReglePrevisionnelle> listPrevisions = reglePrevisionnelleDao.loadAll();
            nextPrevision = listPrevisions.get(0);

            //recalculerTousIntervalles();
            publishProgress(100);
            return null;
        }

        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.GONE);
            textDate.setText(DateUtils.ecrireDate(nextPrevision.getDate()));
            Toast.makeText(AccueilActivity.this, R.string.text_DB_created, Toast.LENGTH_LONG).show();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        protected void onProgressUpdate(Integer... integer) {
            progressBar.setProgress(integer[0],true);
        }
    }

    private void remplirBDDefaut() {
        remplirBDRegle();
    }

    private void remplirBDRegle() {
        if (regleDao.count() == 0) {
            Regle regle = new Regle();
            Date date = DateUtils.creerDateFromString("14/05/2017");
            regle.setDate(date);
            regle.setDateString(DateUtils.ecrireDate(date));
            regleDao.insert(regle);

            enregistrerRegle("08/06/2017");
            enregistrerRegle("04/07/2017");
            enregistrerRegle("29/07/2017");
            enregistrerRegle("26/08/2017");
            enregistrerRegle("21/09/2017");
            enregistrerRegle("16/10/2017");
            enregistrerRegle("11/11/2017");
            enregistrerRegle("05/12/2017");
            enregistrerRegle("30/12/2017");
            enregistrerRegle("25/01/2018");
            enregistrerRegle("20/02/2018");
            enregistrerRegle("17/03/2018");
            enregistrerRegle("10/04/2018");
            enregistrerRegle("10/05/2018");
            enregistrerRegle("05/06/2018");
            enregistrerRegle("01/07/2018");
            enregistrerRegle("29/07/2018");
            enregistrerRegle("24/08/2018");
            enregistrerRegle("19/09/2018");
            enregistrerRegle("15/10/2018");
            enregistrerRegle("11/11/2018");
            enregistrerRegle("08/12/2018");
            enregistrerRegle("05/01/2019");
            enregistrerRegle("31/01/2019");
            enregistrerRegle("21/02/2019");
            enregistrerRegle("20/03/2019");
            enregistrerRegle("17/04/2019");
            enregistrerRegle("13/05/2019");
            enregistrerRegle("07/06/2019");
            enregistrerRegle("30/06/2019");
            enregistrerRegle("28/07/2019");
            enregistrerRegle("22/08/2019");
            enregistrerRegle("19/09/2019");
            enregistrerRegle("15/10/2019");
            enregistrerRegle("10/11/2019");
            enregistrerRegle("07/12/2019");
            enregistrerRegle("01/01/2020");
            enregistrerRegle("27/01/2020");
            enregistrerRegle("22/02/2020");
            enregistrerRegle("20/03/2020");
            enregistrerRegle("18/04/2020");
            enregistrerRegle("16/05/2020");
            enregistrerRegle("09/06/2020");
            enregistrerRegle("08/07/2020");

           /* regle = new Regle();
            date = DateUtils.creerDateFromString("29/05/2020");
            regle.setDate(date);
            regle.setDateString(DateUtils.ecrireDate(date));
            Double delta = DateUtils.getDaysBetweenDates(trouverReglePrecedente(regle).getDate(),regle.getDate());
            regle.setIntervalle((int) Math.round(delta));
            regleDao.insert(regle);

            regle = new Regle();
            date = DateUtils.creerDateFromString("23/06/2020");
            regle.setDate(date);
            regle.setDateString(DateUtils.ecrireDate(date));
            delta = DateUtils.getDaysBetweenDates(trouverReglePrecedente(regle).getDate(),regle.getDate());
            regle.setIntervalle((int) Math.round(delta));
            regleDao.insert(regle);*/
        }
    }
}
