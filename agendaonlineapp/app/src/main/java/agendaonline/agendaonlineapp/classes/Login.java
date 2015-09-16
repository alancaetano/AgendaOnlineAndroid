package agendaonline.agendaonlineapp.classes;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by AlanNunes on 24/07/2015.
 */
public class Login {

    private static final String CHAVE_DADOS_LOGIN = "DADOS_LOGIN";
    private static final String CHAVE_LOGIN = "LOGIN";
    private static final String CHAVE_SENHA = "SENHA";
    private static final String CHAVE_USUARIOID = "USUARIOID";

    public static String recuperarUsuarioIDLogado(Activity activity){
        SharedPreferences dadosLogin = activity.getApplicationContext().getSharedPreferences(CHAVE_DADOS_LOGIN, Activity.MODE_PRIVATE);
        return dadosLogin.getString(CHAVE_USUARIOID, "");
    }

    public static void salvarLogin(Activity activity, String login, String senha){
        SharedPreferences dadosLogin = activity.getApplicationContext().getSharedPreferences(CHAVE_DADOS_LOGIN, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = dadosLogin.edit();
        edit.clear();
        edit.putString(CHAVE_LOGIN, login);
        edit.putString(CHAVE_SENHA, senha);
        edit.putString(CHAVE_USUARIOID, "1");
        edit.commit();
    }
}
