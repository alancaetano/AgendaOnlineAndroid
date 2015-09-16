package agendaonline.agendaonlineapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Mensagem;
import agendaonline.agendaonlineapp.classes.Usuario;


public class ChatActivity extends ActionBarActivity {

    private ListView listView;
    private Conversa conversa;

    public void setConversa(Conversa conversa){
        this.conversa = conversa;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Mensagem msg = new Mensagem();
        msg.setNomeRemetente("professor");
        msg.setDataEnvio(new java.util.Date());
        msg.setTexto("mensagem do professor...");

        Mensagem msg2 = new Mensagem();
        msg2.setNomeRemetente("pai");
        msg2.setDataEnvio(new java.util.Date());
        msg2.setTexto("mensagem do pai...");

        Conversa c = new Conversa();
        c.setMensagens(new ArrayList<Mensagem>());
        c.getMensagens().add(msg);
        c.getMensagens().add(msg2);

        c.setNomeRemetente("professor");

        conversa = c;

        this.listView = (ListView)findViewById(R.id.lista_chat);

        this.setTitle(conversa.getNomeRemetente());

        ChatListViewAdapter adapter = new ChatListViewAdapter(this, conversa);
        this.listView.setAdapter(adapter);


        //mock
        /*Usuario u = new Usuario();
        u.setUsuarioID("1");
        u.setNome("Alan Nunes");

        Usuario u2 = new Usuario();
        u2.setUsuarioID("2");
        u2.setNome("Professora");

        this.conversa = new Conversa();
        conversa.setRemetente(u2);

        Mensagem m1 = new Mensagem();
        m1.setUsuario(u2);
        m1.setMensagem("mensagem do professor...");
        m1.setDataEnvio(new Date());

        Mensagem m2 = new Mensagem();
        m2.setUsuario(u);
        m2.setMensagem("mensagem do Alan...");
        m2.setDataEnvio(new Date());

        List<Mensagem> msgs = new ArrayList<Mensagem>();
        msgs.add(m1);
        msgs.add(m2);

        conversa.setMensagens(msgs);

        //fim do mock
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
