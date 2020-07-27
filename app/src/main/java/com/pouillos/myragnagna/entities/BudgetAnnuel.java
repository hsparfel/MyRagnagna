package com.pouillos.myragnagna.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

@Entity
public class BudgetAnnuel {

    @Id
    private Long id;

    @NotNull
    private Double montant;

    @NotNull
    private Date date;

    @NotNull
    private String dateString;

    @NotNull
    private int annee;

    @Generated(hash = 2073552243)
    public BudgetAnnuel(Long id, @NotNull Double montant, @NotNull Date date,
            @NotNull String dateString, int annee) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.dateString = dateString;
        this.annee = annee;
    }

    @Generated(hash = 160429945)
    public BudgetAnnuel() {
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

    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }



    

}
