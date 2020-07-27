package com.pouillos.myragnagna.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

@Entity
public class BudgetMensuel {

    @Id
    private Long id;

    @NotNull
    private Double montant;

    @NotNull
    private Date date;

    @NotNull
    private String dateString;

    @NotNull
    private int mois;

    @NotNull
    private int annee;

    @Generated(hash = 1588531276)
    public BudgetMensuel(Long id, @NotNull Double montant, @NotNull Date date,
            @NotNull String dateString, int mois, int annee) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.dateString = dateString;
        this.mois = mois;
        this.annee = annee;
    }

    @Generated(hash = 483203538)
    public BudgetMensuel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public int getMois() {
        return this.mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }


    

    

}
