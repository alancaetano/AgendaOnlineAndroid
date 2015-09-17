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
import agendaonline.agendaonlineapp.classes.Usuario;

/**
 * Created by AlanNunes on 30/08/2015.
 */
public class BancoDeDadosHelper extends SQLiteOpenHelper {

    public static final int VERSAO = 1;
    public static final String NOME_BANCO = "base_app.db";

    public static final String[] COMANDOS_CRIACAO = new String[]{Usuario.COMANDO_CRIACAO, Conversa.COMANDO_CRIACAO, Mensagem.COMANDO_CRIACAO};
    public static final String[] COMANDOS_DELECAO = new String[]{Mensagem.COMANDO_DELECAO, Conversa.COMANDO_DELECAO, Usuario.COMANDO_DELECAO};

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
        values.put(Conversa.COLUNA_ID_REMETENTE, conversa.getIdRemetente());

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
        values.put(Mensagem.COLUNA_ID_REMETENTE, mensagem.getIdRemetente());
        values.put(Mensagem.COLUNA_TEXTO, mensagem.getTexto());

        db.insert(Conversa.TABELA, null, values);
    }

    public List<Usuario> RecuperarUsuarios(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select id, nome from usuario ";

        Cursor cursor = db.rawQuery(query, new String[]{});

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getString(0));
            usuario.setNome(cursor.getString(1));
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public List<Mensagem> RecuperarMensagens(String idConversa){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select m.id, m.dt_envio, m.id_remetente, m.texto, u.nome " +
                        " from mensagem m " +
                        " join usuario u on m.id_remetente = u.id " +
                        " where m.id_conversa = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{idConversa});

        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        while(cursor.moveToNext()){
            Mensagem msg = new Mensagem();
            msg.setId(cursor.getString(0));
            msg.setDataEnvio(Util.ConverterTextSQLiteParaData(cursor.getString(1)));
            msg.setIdRemetente(cursor.getString(2));
            msg.setTexto(cursor.getString(3));
            msg.setNomeRemetente(cursor.getString(4));
            mensagens.add(msg);
        }

        return mensagens;
    }
}
