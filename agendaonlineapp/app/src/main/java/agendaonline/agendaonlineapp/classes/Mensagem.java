package agendaonline.agendaonlineapp.classes;

import java.util.Date;

/**
 * Created by AlanNunes on 09/08/2015.
 */
public class Mensagem {
    public static final String TABELA = "mensagem";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_ID_CONVERSA = "id_conversa";
    public static final String COLUNA_ID_REMETENTE = "id_remetente";
    public static final String COLUNA_TEXTO = "texto";
    public static final String COLUNA_DATA_ENVIO = "dt_envio";
    public static final String COMANDO_CRIACAO = "create table "+ TABELA + " ("+
                                                    COLUNA_ID + " TEXT PRIMARY KEY," +
                                                    COLUNA_ID_CONVERSA + " TEXT, " +
                                                    COLUNA_TEXTO + " TEXT, " +
                                                    COLUNA_DATA_ENVIO + " TEXT, " +
                                                    COLUNA_ID_REMETENTE + " TEXT )";
    public static final String COMANDO_DELECAO = "drop table "+ TABELA;

    private String id;
    private String idConversa;
    private String idRemetente;
    private String nomeRemetente;
    private String texto;
    private Date dataEnvio;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getIdConversa(){
        return this.idConversa;
    }

    public void setIdConversa(String idConversa){
        this.idConversa = idConversa;
    }

    public String getIdRemetente(){
        return idRemetente;
    }

    public void setIdRemetente(String idRemetente){
        this.idRemetente = idRemetente;
    }

    public String getNomeRemetente() {
        return this.nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente)
    {
        this.nomeRemetente = nomeRemetente;
    }

    public String getTexto(){
        return this.texto;
    }

    public void setTexto(String texto){
        this.texto = texto;
    }

    public Date getDataEnvio(){
        return this.dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
