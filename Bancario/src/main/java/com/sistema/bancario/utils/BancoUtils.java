package com.sistema.bancario.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class BancoUtils {

    public static Timestamp getFhasta(){
        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt_1 = objSDF.parse("2999-12-31 00:00:00");
            Timestamp fhasta =new Timestamp(dt_1.getTime());
            return fhasta;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp fechaActual(){
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
