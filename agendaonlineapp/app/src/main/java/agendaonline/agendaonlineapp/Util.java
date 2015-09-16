package agendaonline.agendaonlineapp;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by AlanNunes on 08/09/2015.
 */
public class Util {
    public static String GerarID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String ConverterDataParaSQLite(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        return sdf.format(data);
    }

    public static Date ConverterTextSQLiteParaData(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }
}
