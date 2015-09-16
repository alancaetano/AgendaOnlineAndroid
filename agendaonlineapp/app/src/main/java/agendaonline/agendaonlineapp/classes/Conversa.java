package agendaonline.agendaonlineapp.classes;

import java.util.List;

/**
 * Created by AlanNunes on 09/08/2015.
 */
public class Conversa {
    public static final String TABELA = "conversa";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME_REMETENTE = "nome_remetente";
    public static final String COMANDO_CRIACAO = "create table "+ TABELA + " ("+
                                                    COLUNA_ID + " TEXT PRIMARY KEY," +
                                                    COLUNA_NOME_REMETENTE + " TEXT )";
    public static final String COMANDO_DELECAO = "drop table "+ TABELA;

    private String id;
    private String nomeRemetente;
    private List<Mensagem> mensagens;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNomeRemetente() {
        return this.nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente)
    {
        this.nomeRemetente = nomeRemetente;
    }

    public List<Mensagem> getMensagens(){
        return this.mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}
