package agendaonline.agendaonlineapp.classes;

/**
 * Created by AlanNunes on 23/07/2015.
 */
public class Usuario {
    private String id;
    private String nome;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public static final String TABELA = "usuario";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";

    public static final String COMANDO_CRIACAO = "create table "+ TABELA + " ("+
            COLUNA_ID + " TEXT PRIMARY KEY," +
            COLUNA_NOME + " TEXT  )";
    public static final String COMANDO_DELECAO = "drop table "+ TABELA;
}


