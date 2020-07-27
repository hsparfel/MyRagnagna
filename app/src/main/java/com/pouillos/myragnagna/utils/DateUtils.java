package com.pouillos.myragnagna.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtils {


    public static String ecrireDateLettre(Date date) {
        Map<Integer, String> mapJours = new HashMap<>();
        mapJours.put(1,"Dimanche");
        mapJours.put(2,"Lundi");
        mapJours.put(3,"Mardi");
        mapJours.put(4,"Mercredi");
        mapJours.put(5,"Jeudi");
        mapJours.put(6,"Vendredi");
        mapJours.put(7,"Samedi");

        Map<Integer, String> mapMois = new HashMap<>();
        mapMois.put(0,"Janvier");
        mapMois.put(1,"Fevrier");
        mapMois.put(2,"Mars");
        mapMois.put(3,"Avril");
        mapMois.put(4,"Mai");
        mapMois.put(5,"Juin");
        mapMois.put(6,"Juillet");
        mapMois.put(7,"Aout");
        mapMois.put(8,"Septembre");
        mapMois.put(9,"Octobre");
        mapMois.put(10,"Novembre");
        mapMois.put(11,"Decembre");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        String dateMaj = "";
        dateMaj += mapJours.get(dayOfWeek)+" ";
        dateMaj += day+" ";
        dateMaj += mapMois.get(month)+" ";
        dateMaj += date.getYear()+1900;

        return dateMaj;
    }

    public static String ecrireMoisAnneeLettre(Date date) {

        Map<Integer, String> mapMois = new HashMap<>();
        mapMois.put(0,"Janvier");
        mapMois.put(1,"Fevrier");
        mapMois.put(2,"Mars");
        mapMois.put(3,"Avril");
        mapMois.put(4,"Mai");
        mapMois.put(5,"Juin");
        mapMois.put(6,"Juillet");
        mapMois.put(7,"Aout");
        mapMois.put(8,"Septembre");
        mapMois.put(9,"Octobre");
        mapMois.put(10,"Novembre");
        mapMois.put(11,"Decembre");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        String dateMaj = "";
        dateMaj += mapMois.get(month)+" ";
        dateMaj += date.getYear()+1900;
        return dateMaj;
    }

    public static String ecrireDate(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateMaj = simpleDateFormat.format(date);
        return dateMaj;
    }

    public static String ecrireDateHeure(Date date) {
        String pattern = "dd/MM/yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateMaj = simpleDateFormat.format(date);
        return dateMaj;
    }

    public static Date ajouterJourArrondi(Date date, int nbJours, int heure) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,nbJours);
        calendar.set(Calendar.HOUR_OF_DAY, heure);
        calendar.set(Calendar.MINUTE,0);
        Date dateCalculee = calendar.getTime();
        return dateCalculee;
    }

    public static double getDaysBetweenDates(Date theEarlierDate, Date theLaterDate) {
        double result = Double.POSITIVE_INFINITY;
        if (theEarlierDate != null && theLaterDate != null) {
            final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
            Calendar aCal = Calendar.getInstance();
            aCal.setTime(theEarlierDate);
            long aFromOffset = aCal.get(Calendar.DST_OFFSET);
            aCal.setTime(theLaterDate);
            long aToOffset = aCal.get(Calendar.DST_OFFSET);
            long aDayDiffInMili = (theLaterDate.getTime() + aToOffset) - (theEarlierDate.getTime() + aFromOffset);
            result = ((double) aDayDiffInMili / MILLISECONDS_PER_DAY);
        }
        return result;
    }

    public static Date creerDateFromString(String dateString) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        try {
            date = format.parse(dateString);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
