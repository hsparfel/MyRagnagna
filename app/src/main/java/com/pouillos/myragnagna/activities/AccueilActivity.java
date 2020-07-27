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
import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.activities.afficher.AfficherDepenseActivity;
import com.pouillos.myragnagna.dao.BudgetAnnuelDao;
import com.pouillos.myragnagna.dao.BudgetDao;
import com.pouillos.myragnagna.dao.BudgetMensuelDao;
import com.pouillos.myragnagna.entities.Budget;
import com.pouillos.myragnagna.entities.BudgetAnnuel;
import com.pouillos.myragnagna.entities.BudgetMensuel;
import com.pouillos.myragnagna.entities.CategorieDepense;
import com.pouillos.myragnagna.entities.Depense;
import com.pouillos.myragnagna.enumeration.TypeDepense;
import com.pouillos.myragnagna.utils.DateUtils;

import java.util.Date;
import java.util.List;

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

    int nbMois = 60;

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
            publishProgress(60);
            updateBudget();
            publishProgress(70);
            createBudgetMensuel();
            publishProgress(80);
            createBudgetAnnuel();
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

    private void updateBudget() {
        List<Depense> listDepenseBD = depenseDao.loadAll();
        Budget budget;
        for (Depense currentDepense : listDepenseBD) {
            if (!currentDepense.getIsBudgeted()) {
                if (currentDepense.getCategorieDepense().getTypeDepense() == TypeDepense.Courante
                || currentDepense.getCategorieDepense().getTypeDepense() == TypeDepense.Occasionnelle) {
                    budget = new Budget();
                    budget.setDepense(currentDepense);
                    Date date = DateUtils.dateDebutMois(currentDepense.getDate());
                    budget.setDate(date);
                    budget.setDateString(DateUtils.ecrireDate(date));
                    budget.setAnnee(currentDepense.getAnnee());
                    budget.setMois(currentDepense.getMois());
                    budget.setMontant(currentDepense.getMontant());
                    budgetDao.insert(budget);
                    currentDepense.setIsBudgeted(true);
                    depenseDao.update(currentDepense);
                } else if (currentDepense.getCategorieDepense().getTypeDepense() == TypeDepense.Fixe) {
                    if (!currentDepense.getIsRecurrent()) {
                        switch(currentDepense.getFrequenceDepense()) {
                            case Ponctuel:
                                enregistrerDepenseFixe(currentDepense,1,1);
                                break;
                            case Mensuel:
                                enregistrerDepenseFixe(currentDepense,1,1);
                                break;
                            case Bimestriel:
                                enregistrerDepenseFixe(currentDepense,2,2);
                                break;
                            case Trimestriel:
                                enregistrerDepenseFixe(currentDepense,3,3);
                                break;
                            case Quadrimestriel:
                                enregistrerDepenseFixe(currentDepense,4,4);
                                break;
                            case Semestriel:
                                enregistrerDepenseFixe(currentDepense,6,6);
                                break;
                            case Annuel:
                                enregistrerDepenseFixe(currentDepense,12,12);
                                break;
                            case Default:
                                break;
                        }
                    } else {
                        switch (currentDepense.getFrequenceDepense()) {
                            case Ponctuel:
                                enregistrerDepenseFixe(currentDepense, 1, 60);
                                break;
                            case Mensuel:
                                enregistrerDepenseFixe(currentDepense, 1, 60);
                                break;
                            case Bimestriel:
                                enregistrerDepenseFixe(currentDepense, 2, 60);
                                break;
                            case Trimestriel:
                                enregistrerDepenseFixe(currentDepense, 3, 60);
                                break;
                            case Quadrimestriel:
                                enregistrerDepenseFixe(currentDepense, 4, 60);
                                break;
                            case Semestriel:
                                enregistrerDepenseFixe(currentDepense, 6, 60);
                                break;
                            case Annuel:
                                enregistrerDepenseFixe(currentDepense, 12, 60);
                                break;
                            case Default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private void enregistrerDepenseFixe(Depense depense, int nbMoisLissage, int nbMoisRepetition ) {
        for (int i=0;i<nbMoisRepetition;i++) {
            Budget budget = new Budget();
            budget.setDepense(depense);
            Date date = DateUtils.ajouterMois(DateUtils.dateDebutMois(depense.getDate()),i);
            budget.setDate(date);
            budget.setDateString(DateUtils.ecrireDate(date));
            budget.setAnnee(Integer.parseInt(DateUtils.recupAnnee(date)));
            budget.setMois(Integer.parseInt(DateUtils.recupMois(date)));
            budget.setMontant(Math.round(depense.getMontant()/nbMoisLissage * 100.0) / 100.0);
            budgetDao.insert(budget);
        }
        depense.setIsBudgeted(true);
        depenseDao.update(depense);
    }

    private void createBudgetMensuel() {
        budgetMensuelDao.deleteAll();
        Date dateDebut = new Date();
        Date dateFin = new Date();
        List<Budget> listBudgetBD = budgetDao.loadAll();
        if (listBudgetBD.size() >0) {
            dateDebut = listBudgetBD.get(0).getDate();
            dateFin = listBudgetBD.get(0).getDate();
            for (Budget currentBudget : listBudgetBD) {
                if (currentBudget.getDate().before(dateDebut)) {
                    dateDebut = currentBudget.getDate();
                } else if (currentBudget.getDate().after(dateFin)) {
                    dateFin = currentBudget.getDate();
                }
            }
        }
        for (Date date = dateDebut;date.before(DateUtils.ajouterMois(dateFin,1));date = DateUtils.ajouterMois(date,1)) {
            List<Budget> budgets = budgetDao.queryBuilder()
                    .where(BudgetDao.Properties.Date.eq(date))
                    .list();
            List<BudgetMensuel> budgetMensuels = budgetMensuelDao.queryBuilder()
                    .where(BudgetMensuelDao.Properties.Date.eq(date))
                    .list();
            BudgetMensuel budgetMensuel = new BudgetMensuel();
            if (budgetMensuels.size() == 0) {
                budgetMensuel.setDate(date);
                budgetMensuel.setDateString(DateUtils.ecrireDate(date));
                budgetMensuel.setMois(Integer.parseInt(DateUtils.recupMois(date)));
                budgetMensuel.setAnnee((Integer.parseInt(DateUtils.recupAnnee(date))));
                budgetMensuel.setMontant(0.0);
                budgetMensuelDao.insert(budgetMensuel);
                budgetMensuels.add(budgetMensuel);
            } else {
                budgetMensuel = budgetMensuels.get(0);
            }
            for (Budget currentbudget : budgets) {
                budgetMensuel.setMontant(budgetMensuel.getMontant()+currentbudget.getMontant());
                budgetMensuel.setMontant(Math.round(budgetMensuel.getMontant() * 100.0) / 100.0);
            }
            budgetMensuelDao.update(budgetMensuel);
        }
    }

    private void createBudgetAnnuel() {
        budgetAnnuelDao.deleteAll();
        Date dateDebut = new Date();
        Date dateFin = new Date();
        List<Budget> listBudgetBD = budgetDao.loadAll();
        if (listBudgetBD.size() >0) {
            dateDebut = listBudgetBD.get(0).getDate();
            dateFin = listBudgetBD.get(0).getDate();
            for (Budget currentBudget : listBudgetBD) {
                if (currentBudget.getDate().before(dateDebut)) {
                    dateDebut = currentBudget.getDate();
                } else if (currentBudget.getDate().after(dateFin)) {
                    dateFin = currentBudget.getDate();
                }
            }
        }
        for (Date date = dateDebut;date.before(DateUtils.ajouterAnnee(dateFin,1));date = DateUtils.ajouterAnnee(date,1)) {
            List<Budget> budgets = budgetDao.queryBuilder()
                    .where(BudgetDao.Properties.Annee.eq(DateUtils.recupAnnee(date)))
                    .list();
            List<BudgetAnnuel> budgetAnnuels = budgetAnnuelDao.queryBuilder()
                    .where(BudgetAnnuelDao.Properties.Annee.eq(DateUtils.recupAnnee(date)))
                    .list();
            BudgetAnnuel budgetAnnuel = new BudgetAnnuel();
            if (budgetAnnuels.size() == 0) {
                budgetAnnuel.setDate(date);
                budgetAnnuel.setDateString(DateUtils.ecrireDate(date));
                budgetAnnuel.setAnnee((Integer.parseInt(DateUtils.recupAnnee(date))));
                budgetAnnuel.setMontant(0.0);
                budgetAnnuelDao.insert(budgetAnnuel);
                budgetAnnuels.add(budgetAnnuel);
            } else {
                budgetAnnuel = budgetAnnuels.get(0);
            }
            for (Budget currentbudget : budgets) {
                budgetAnnuel.setMontant(budgetAnnuel.getMontant()+currentbudget.getMontant());
                budgetAnnuel.setMontant(Math.round(budgetAnnuel.getMontant() * 100.0) / 100.0);
            }
            budgetAnnuelDao.update(budgetAnnuel);
        }
    }
}
