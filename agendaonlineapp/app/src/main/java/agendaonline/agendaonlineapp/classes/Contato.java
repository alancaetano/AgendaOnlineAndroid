package agendaonline.agendaonlineapp.classes;

import java.util.List;

/**
 * Created by AlanNunes on 09/08/2015.
 */
public class Contato {
    public static final String TABELA = "contato";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome_contato";
    public static final String COMANDO_CRIACAO = "create table "+ TABELA + " ("+
                                                    COLUNA_ID + " TEXT PRIMARY KEY," +
                                                    COLUNA_NOME + " TEXT )";
    public static final String COMANDO_DELECAO = "drop table "+ TABELA;

    private String id;
    private String nome;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
