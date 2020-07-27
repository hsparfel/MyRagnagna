package com.pouillos.myragnagna.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

@Entity
public class ReglePrevisionnelle implements Comparable<ReglePrevisionnelle>{

    @Id
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private String dateString;

    @Generated(hash = 330013291)
    public ReglePrevisionnelle(Long id, @NotNull Date date,
            @NotNull String dateString) {
        this.id = id;
        this.date = date;
        this.dateString = dateString;
    }

    @Generated(hash = 568916734)
    public ReglePrevisionnelle() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int compareTo(ReglePrevisionnelle o) {
        return this.date.compareTo(o.date);
    }

}
