package com.pouillos.mydepenses.activities.afficher;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pouillos.mydepenses.R;
import com.pouillos.mydepenses.activities.NavDrawerActivity;
import com.pouillos.mydepenses.entities.CategorieDepense;
import com.pouillos.mydepenses.enumeration.FrequenceDepense;
import com.pouillos.mydepenses.enumeration.TypeDepense;
import com.pouillos.mydepenses.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;

public class AfficherDepenseActivity extends NavDrawerActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.textMontant)
    TextInputEditText textMontant;
    @BindView(R.id.layoutMontant)
    TextInputLayout layoutMontant;

    @BindView(R.id.textDetail)
    TextInputEditText textDetail;
    @BindView(R.id.layoutDetail)
    TextInputLayout layoutDetail;

    @BindView(R.id.textDate)
    TextInputEditText textDate;
    @BindView(R.id.layoutDate)
    TextInputLayout layoutDate;
    Date date;

    @BindView(R.id.switchRecurrent)
    SwitchMaterial switchRecurrent;

    @BindView(R.id.fabSave)
    FloatingActionButton fabSave;

    List<TypeDepense> listeTypeDepense = new ArrayList<>();
    TypeDepense typeDepenseSelected;


    List<CategorieDepense> listeCategorieDepense = new ArrayList<>();
    CategorieDepense categorieDepenseSelected;
    @BindView(R.id.selectCategorieDepense)
    AutoCompleteTextView selectCategorieDepense;
    @BindView(R.id.listCategorieDepense)
    TextInputLayout listCategorieDepense;

    List<FrequenceDepense> listeFrequenceDepense = new ArrayList<>();
    FrequenceDepense frequenceDepenseSelected;


    @BindView(R.id.chipFixe)
    Chip chipFixe;
    @BindView(R.id.chipCourante)
    Chip chipCourante;
    @BindView(R.id.chipOccasionnelle)
    Chip chipOccasionnelle;

    @BindView(R.id.chipPonctuel)
    Chip chipPonctuel;
    @BindView(R.id.chipHebdomadaire)
    Chip chipHebdomadaire;
    @BindView(R.id.chipBimensuel)
    Chip chipBimensuel;
    @BindView(R.id.chipMensuel)
    Chip chipMensuel;
    @BindView(R.id.chipBimestriel)
    Chip chipBimestriel;
    @BindView(R.id.chipTrimestriel)
    Chip chipTrimestriel;
    @BindView(R.id.chipQuadrimestriel)
    Chip chipQuadrimestriel;
    @BindView(R.id.chipBiannuel)
    Chip chipBiannuel;
    @BindView(R.id.chipAnnuel)
    Chip chipAnnuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_afficher_depense);
        // 6 - Configure all views

        ButterKnife.bind(this);
      /*  activeUser = findActiveUser();
        List<Contact> listContact = contactDao.loadAll();
        if (listContact.size()>0){*/
           //  6 - Configure all views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        //}

        //limiter digits du montant
        textMontant.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});

      //  traiterIntent();

        textDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog(view, textDate,false,false,null,null);
                    textDate.clearFocus();
                    date = convertStringToDate(textDate.getText().toString());
                }

            }
        });

        hideAllChipsFrequenceExceptOne(null);

        date = new Date();
        textDate.setText(DateUtils.ecrireDate(date));
        

    }

    public class DecimalDigitsInputFilter implements InputFilter {

        private final int digitsBeforeZero;
        private final int digitsAfterZero;
        private Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            this.digitsBeforeZero = digitsBeforeZero;
            this.digitsAfterZero = digitsAfterZero;
            applyPattern(digitsBeforeZero, digitsAfterZero);
        }

        private void applyPattern(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)|(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (dest.toString().contains(".") || source.toString().contains("."))
                applyPattern(digitsBeforeZero + 2, digitsAfterZero);
            else
                applyPattern(digitsBeforeZero, digitsAfterZero);

            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }

    }

    private void uncheckedAllChipsFrequence() {
        chipPonctuel.setChecked(false);
        chipHebdomadaire.setChecked(false);
        chipBimensuel.setChecked(false);
        chipMensuel.setChecked(false);
        chipBimestriel.setChecked(false);
        chipTrimestriel.setChecked(false);
        chipQuadrimestriel.setChecked(false);
        chipBiannuel.setChecked(false);
        chipAnnuel.setChecked(false);

        switchRecurrent.setChecked(false);

        selectCategorieDepense.setText(null);
    }

    private void showAllChipsFrequence() {
        chipPonctuel.setVisibility(View.VISIBLE);
        chipHebdomadaire.setVisibility(View.VISIBLE);
        chipBimensuel.setVisibility(View.VISIBLE);
        chipMensuel.setVisibility(View.VISIBLE);
        chipBimestriel.setVisibility(View.VISIBLE);
        chipTrimestriel.setVisibility(View.VISIBLE);
        chipQuadrimestriel.setVisibility(View.VISIBLE);
        chipBiannuel.setVisibility(View.VISIBLE);
        chipAnnuel.setVisibility(View.VISIBLE);

        switchRecurrent.setVisibility(View.VISIBLE);
    }

    private void hideAllChipsFrequenceExceptOne(Chip chip) {

        if (chip == null) {
            switchRecurrent.setVisibility(View.GONE);
        }

        if (chip != chipPonctuel) {
            chipPonctuel.setVisibility(View.GONE);
        }
        if (chip != chipHebdomadaire) {
            chipHebdomadaire.setVisibility(View.GONE);
        }
        if (chip != chipBimensuel) {
            chipBimensuel.setVisibility(View.GONE);
        }
        if (chip != chipMensuel) {
            chipMensuel.setVisibility(View.GONE);
        }
        if (chip != chipBimestriel) {
            chipBimestriel.setVisibility(View.GONE);
        }
        if (chip != chipTrimestriel) {
            chipTrimestriel.setVisibility(View.GONE);
        }
        if (chip != chipQuadrimestriel) {
            chipQuadrimestriel.setVisibility(View.GONE);
        }
        if (chip != chipBiannuel) {
            chipBiannuel.setVisibility(View.GONE);
        }
        if (chip != chipAnnuel) {
            chipAnnuel.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.chipPonctuel)
    public void setchipPonctuelClick() {
        if (chipPonctuel.isChecked()) {
            Snackbar.make(chipPonctuel, FrequenceDepense.Ponctuel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipPonctuel).show();
            hideAllChipsFrequenceExceptOne(chipPonctuel);
        } else {
            showAllChipsFrequence();
        }
    }
    @OnClick(R.id.chipHebdomadaire)
    public void setchipHebdomadaireClick() {
        if (chipHebdomadaire.isChecked()) {
            Snackbar.make(chipHebdomadaire, FrequenceDepense.Hebdomadaire.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipHebdomadaire).show();
            hideAllChipsFrequenceExceptOne(chipHebdomadaire);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipBimensuel)
    public void setchipBimensuelClick() {
        if (chipBimensuel.isChecked()) {
            Snackbar.make(chipBimensuel, FrequenceDepense.Bimensuel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipBimensuel).show();
            hideAllChipsFrequenceExceptOne(chipBimensuel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipMensuel)
    public void setchipMensuelClick() {
        if (chipMensuel.isChecked()) {
            Snackbar.make(chipMensuel, FrequenceDepense.Mensuel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipMensuel).show();
            hideAllChipsFrequenceExceptOne(chipMensuel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipBimestriel)
    public void setchipBimestrielClick() {
        if (chipBimestriel.isChecked()) {
            Snackbar.make(chipBimestriel, FrequenceDepense.Bimestriel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipBimestriel).show();
            hideAllChipsFrequenceExceptOne(chipBimestriel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipTrimestriel)
    public void setchipTrimestrielClick() {
        if (chipTrimestriel.isChecked()) {
            Snackbar.make(chipTrimestriel, FrequenceDepense.Trimestriel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipTrimestriel).show();
            hideAllChipsFrequenceExceptOne(chipTrimestriel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipQuadrimestriel)
    public void setchipQuadrimestrielClick() {
        if (chipQuadrimestriel.isChecked()) {
            Snackbar.make(chipQuadrimestriel, FrequenceDepense.Quadrimestriel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipQuadrimestriel).show();
            hideAllChipsFrequenceExceptOne(chipQuadrimestriel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipBiannuel)
    public void setchipBiannuelleClick() {
        if (chipBiannuel.isChecked()) {
            Snackbar.make(chipBiannuel, FrequenceDepense.Biannuel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipBiannuel).show();
            hideAllChipsFrequenceExceptOne(chipBiannuel);
        } else {
            showAllChipsFrequence();
        }

    }
    @OnClick(R.id.chipAnnuel)
    public void setchipAnnuelClick() {
        if (chipAnnuel.isChecked()) {
            Snackbar.make(chipAnnuel, FrequenceDepense.Annuel.detail, Snackbar.LENGTH_SHORT).setAnchorView(chipAnnuel).show();
            hideAllChipsFrequenceExceptOne(chipAnnuel);
        } else {
            showAllChipsFrequence();
        }

    }




    @OnClick(R.id.chipFixe)
    public void setChipFixeClick() {
        if (chipFixe.isChecked()) {
            listeCategorieDepense = new ArrayList<>();
            List<CategorieDepense> liste = categorieDepenseDao.loadAll();
            if (liste.size() > 0) {
                for (CategorieDepense currentCategorieDepense : liste) {
                    if (currentCategorieDepense.getTypeDepense() == TypeDepense.Fixe) {
                        listeCategorieDepense.add(currentCategorieDepense);
                    }
                }
                Collections.sort(listeCategorieDepense);
                buildDropdownMenu(listeCategorieDepense, AfficherDepenseActivity.this, selectCategorieDepense);
                selectCategorieDepense.setOnItemClickListener(this);
            }
            uncheckedAllChipsFrequence();
            showAllChipsFrequence();
            chipFixe.setClickable(false);
            chipCourante.setClickable(true);
            chipOccasionnelle.setClickable(true);
        }

    }

    @OnClick(R.id.chipCourante)
    public void setChipCouranteClick() {
        if (chipCourante.isChecked()) {
            listeCategorieDepense = new ArrayList<>();
            List<CategorieDepense> liste = categorieDepenseDao.loadAll();
            if (liste.size() > 0) {
                for (CategorieDepense currentCategorieDepense : liste) {
                    if (currentCategorieDepense.getTypeDepense() == TypeDepense.Courante) {
                        listeCategorieDepense.add(currentCategorieDepense);
                    }
                }
                Collections.sort(listeCategorieDepense);
                buildDropdownMenu(listeCategorieDepense, AfficherDepenseActivity.this, selectCategorieDepense);
                selectCategorieDepense.setOnItemClickListener(this);
            }
            uncheckedAllChipsFrequence();
            hideAllChipsFrequenceExceptOne(null);

            chipFixe.setClickable(true);
            chipCourante.setClickable(false);
            chipOccasionnelle.setClickable(true);
        }

    }

    @OnClick(R.id.chipOccasionnelle)
    public void setChipOccasionnelleClick() {
        if (chipOccasionnelle.isChecked()) {
            listeCategorieDepense = new ArrayList<>();
            List<CategorieDepense> liste = categorieDepenseDao.loadAll();
            if (liste.size() > 0) {
                for (CategorieDepense currentCategorieDepense : liste) {
                    if (currentCategorieDepense.getTypeDepense() == TypeDepense.Occasionnelle) {
                        listeCategorieDepense.add(currentCategorieDepense);
                    }
                }
                Collections.sort(listeCategorieDepense);
                buildDropdownMenu(listeCategorieDepense, AfficherDepenseActivity.this, selectCategorieDepense);
                selectCategorieDepense.setOnItemClickListener(this);
            }
            uncheckedAllChipsFrequence();
            hideAllChipsFrequenceExceptOne(null);

            chipFixe.setClickable(true);
            chipCourante.setClickable(true);
            chipOccasionnelle.setClickable(false);
        }

    }



    public void traiterIntent() {
        Intent intent = getIntent();
       /* if (intent.hasExtra("contactId")) {
            Long contactId = intent.getLongExtra("contactId", 0);
            currentContact = contactDao.load(contactId);

            //todo afficher toutes les données
            textName.setText(currentContact.getNom());
            textPhone.setText(currentContact.getTelephone());
            //switchAccident.setChecked(currentContact.i); etc...
            switchAccident.setChecked(currentContact.getIsContactAccident());
            switchEnlevement.setChecked(currentContact.getIsContactEnlevement());
        } else {
            currentContact = new Contact();
        }*/
    }

    @OnClick(R.id.fabSave)
    public void setfabSaveClick() {
        if (isFullRempli()) {
           // currentContact.setNom(textName.getText().toString());
           // currentContact.setTelephone(textPhone.getText().toString());
            //currentContact.set(textTown.getText().toString());
            //currentContact.setTelephone(textPhone.getText().toString());

         /*   if (switchAccident.isChecked()) {
                currentContact.setIsContactAccident(true);
            }
            if (switchEnlevement.isChecked()) {
                currentContact.setIsContactEnlevement(true);
            }
            contactDao.insert(currentContact);*/


            //enregistrer sms perso/defaut
         /*   if (currentContact.getIsContactAccident()){
                SmsAccident smsAccident = new SmsAccident();
                smsAccident.setContactId(currentContact.getId());
                smsAccident.setMessage(recupererParametres().getSmsAccident());
                smsAccidentDao.insert(smsAccident);
            }
            if (currentContact.getIsContactEnlevement()){
                SmsEnlevement smsEnlevement = new SmsEnlevement();
                smsEnlevement.setContactId(currentContact.getId());
                smsEnlevement.setMessage(recupererParametres().getSmsEnlevement());
                smsEnlevementDao.insert(smsEnlevement);
            }

            if (switchAccident.isChecked()) {
                envoyerSms(currentContact,recupererParametres().getSmsInformationAccidentDebut());
            }
            if (switchEnlevement.isChecked()) {
                envoyerSms(currentContact,recupererParametres().getSmsInformationEnlevementDebut());
            }*/


            //ouvrirActiviteSuivante(AfficherDepenseActivity.this,AfficherListeContactActivity.class,true);
        }
    }

    private boolean isFullRempli() {
        boolean bool = true;
     /*   if (!isFilled(textName)) {
            textName.setError("Obligatoire");
            bool = false;
        }

        if (!isFilled(textPhone)) {
            textPhone.setError("Obligatoire");
            bool = false;
        }
        if (!isValidTel(textPhone)) {
            textPhone.setError("Saisie Non Valide  (10 chiffres)");
            bool = false;
        }*/

       /* if (!switchAccident.isChecked() && !switchEnlevement.isChecked()) {
            Toast.makeText(this, "Veuillez selectionner au moins un type d'alerte", Toast.LENGTH_LONG).show();
            bool = false;
        }*/
        return bool;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        categorieDepenseSelected = listeCategorieDepense.get(position);
    }

}
