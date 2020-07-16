package com.pouillos.mydepenses.activities;

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
import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.activities.afficher.AfficherDepenseActivity;
import com.pouillos.mydepenses.entities.CategorieDepense;
import com.pouillos.mydepenses.enumeration.TypeDepense;
import com.pouillos.mydepenses.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AccueilActivity extends NavDrawerActivity {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.fabAddDepense)
    FloatingActionButton fabAddDepense;

    @BindView(R.id.my_progressBar)
    ProgressBar progressBar;

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

    @OnClick(R.id.fabAddDepense)
    public void setFabAddDepenseClick() {
        ouvrirActiviteSuivante(this,AfficherDepenseActivity.class,false);
    }

    private class AsyncTaskRunnerBD extends AsyncTask<Void, Integer, Void> {

        protected Void doInBackground(Void...voids) {
            publishProgress(0);
            publishProgress(10);
            remplirBDDefaut();
            publishProgress(50);
            remplirBDExemple();
            publishProgress(100);
            return null;
        }

        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(AccueilActivity.this, R.string.text_DB_created, Toast.LENGTH_LONG).show();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        protected void onProgressUpdate(Integer... integer) {
            progressBar.setProgress(integer[0],true);
        }
    }

    private void remplirBDDefaut() {
        remplirBDCategorieDepense();
    }

    private void remplirBDCategorieDepense() {
        //todo
        if (categorieDepenseDao.count() == 0) {

            CategorieDepense categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Alimentaire");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Electricite");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Telephone");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Internet");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Gaz");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Eau");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Credit Immobilier");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Credit Automobile");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Credit Consommation");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Assurance Habitation");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Assurance Automobile");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Impôt Revenu");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Taxe Habitation");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Impôt Foncier");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Loyer");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Charge Copropriete");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Essence");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Transport");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Restaurant");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Sante");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Scolarite");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Garde");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Loisirs");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Vacances");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Decoration");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Habits");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Cantine");
            categorieDepense.setTypeDepense(TypeDepense.Courante);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Frais Bancaire");
            categorieDepense.setTypeDepense(TypeDepense.Fixe);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Travaux");
            categorieDepense.setTypeDepense(TypeDepense.Occasionnelle);
            categorieDepenseDao.insert(categorieDepense);

            categorieDepense = new CategorieDepense();
            categorieDepense.setNom("Reparation");
            categorieDepense.setTypeDepense(TypeDepense.Occasionnelle);
            categorieDepenseDao.insert(categorieDepense);
        }
    }

    private void remplirBDExemple() {
        //todo
    }
}
