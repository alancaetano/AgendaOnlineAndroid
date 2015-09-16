package agendaonline.agendaonlineapp.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import agendaonline.agendaonlineapp.Util;
import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Mensagem;

/**
 * Created by AlanNunes on 30/08/2015.
 */
public class BancoDeDadosHelper extends SQLiteOpenHelper {

    public static final int VERSAO = 1;
    public static final String NOME_BANCO = "base_app.db";

    public static final String[] COMANDOS_CRIACAO = new String[]{Conversa.COMANDO_CRIACAO, Mensagem.COMANDO_CRIACAO};
    public static final String[] COMANDOS_DELECAO = new String[]{Mensagem.COMANDO_DELECAO, Conversa.COMANDO_DELECAO};

    public BancoDeDadosHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
    public void onCreate(SQLiteDatabase db) {
        for(int i = 0; i < COMANDOS_CRIACAO.length; i++) {
            db.execSQL(COMANDOS_CRIACAO[i]);
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = 0; i < COMANDOS_DELECAO.length; i++) {
            db.execSQL(COMANDOS_DELECAO[i]);
        }

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void InserirConversa(Conversa conversa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Conversa.COLUNA_ID, conversa.getId());
        values.put(Conversa.COLUNA_NOME_REMETENTE, conversa.getNomeRemetente());

        db.insert(Conversa.TABELA, null, values);

        for(int i = 0; i < conversa.getMensagens().size(); i++){
            this.InserirMensagem(conversa.getMensagens().get(i), conversa.getId());
        }
    }

    public void InserirMensagem(Mensagem mensagem, String conversaId){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Mensagem.COLUNA_ID, mensagem.getId());
        values.put(Mensagem.COLUNA_ID_CONVERSA, conversaId);
        values.put(Mensagem.COLUNA_DATA_ENVIO, Util.ConverterDataParaSQLite(mensagem.getDataEnvio()));
        values.put(Mensagem.COLUNA_NOME_REMETENTE, mensagem.getNomeRemetente());
        values.put(Mensagem.COLUNA_TEXTO, mensagem.getTexto());

        db.insert(Conversa.TABELA, null, values);
    }

    public List<Conversa> RecuperarConversas(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(false, Conversa.TABELA, new String[]{Conversa.COLUNA_ID, Conversa.COLUNA_NOME_REMETENTE}, null, null, null, null, null, null, null);

        List<Conversa> conversas = new ArrayList<Conversa>();
        while(cursor.moveToNext()){
            Conversa conversa = new Conversa();
            conversa.setId(cursor.getString(0));
            conversa.setNomeRemetente(cursor.getString(1));
            conversas.add(conversa);
        }

        return conversas;
    }

    public List<Mensagem> RecuperarMensagens(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(false, Mensagem.TABELA, new String[]{
                                        Mensagem.COLUNA_ID,
                                        Mensagem.COLUNA_ID_CONVERSA,
                                        Mensagem.COLUNA_DATA_ENVIO,
                                        Mensagem.COLUNA_NOME_REMETENTE,
                                        Mensagem.COLUNA_TEXTO}, null, null, null, null, null, null, null);

        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        while(cursor.moveToNext()){
            Mensagem msg = new Mensagem();
            msg.setId(cursor.getString(0));
            msg.setIdConversa(cursor.getString(1));
            msg.setDataEnvio(Util.ConverterTextSQLiteParaData(cursor.getString(2)));
            msg.setNomeRemetente(cursor.getString(3));
            msg.setTexto(cursor.getString(4));
            mensagens.add(msg);
        }

        return mensagens;
    }
}
