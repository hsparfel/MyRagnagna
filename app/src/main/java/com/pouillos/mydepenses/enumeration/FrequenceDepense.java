package com.pouillos.mydepenses.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FrequenceDepense {

    //Objets directement construits
    Default(0,"?", "?"),
    Ponctuel(1,"Ponctuel","Une fois"),
    Hebdomadaire(2,"Hebdomadaire","Toutes les semaines"),
    Bimensuel(3,"Bimensuel","Toutes les 2 semaines"),
    Mensuel(4,"Mensuel","Tous les mois"),
    Bimestriel(5,"Bimestriel","Tous les 2 mois"),
    Trimestriel(6,"Trimestriel","Tous les 3 mois"),
    Quadrimestriel(7,"Quadrimestriel","Tous les 4 mois"),
    Biannuel(8,"Biannuel","Tous les 6 mois"),
    Annuel(9,"Annuel","Tous les ans");

    public long id;
    public String name = "";
    public String detail = "";

    //Constructeur
    FrequenceDepense(long id, String name, String detail){
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public String toString(){
        return name;
    }

    public List<FrequenceDepense> listAll() {
        List<FrequenceDepense> listToReturn = new ArrayList<>();
        List<FrequenceDepense> listFrequenceDepense = Arrays.asList(FrequenceDepense.values());
        for (FrequenceDepense frequenceDepense : listFrequenceDepense) {
            if (frequenceDepense != FrequenceDepense.Default) {
                listToReturn.add(frequenceDepense);
            }
        }
        return listToReturn;
    }
}
