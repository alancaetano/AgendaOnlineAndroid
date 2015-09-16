package agendaonline.agendaonlineapp.classes;

/**
 * Created by AlanNunes on 23/07/2015.
 */
public class Usuario {
    private String usuarioID;
    private String nome;
    private TipoUsuario tipoUsuario;

    public String getUsuarioID(){
        return this.usuarioID;
    }

    public void setUsuarioID(String usuarioID){
        this.usuarioID = usuarioID;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario(){
        return this.tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }
}


