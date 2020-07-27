package com.pouillos.myragnagna.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Regle implements Comparable<Regle>{

    @Id
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private String dateString;

    private int intervalle;

    @Generated(hash = 837199643)
    public Regle(Long id, @NotNull Date date, @NotNull String dateString,
            int intervalle) {
        this.id = id;
        this.date = date;
        this.dateString = dateString;
        this.intervalle = intervalle;
    }

    @Generated(hash = 1126260775)
    public Regle() {
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

    public int getIntervalle() {
        return this.intervalle;
    }

    public void setIntervalle(int intervalle) {
        this.intervalle = intervalle;
    }

    @Override
    public int compareTo(Regle o) {
        return o.date.compareTo(this.date);
    }
}
