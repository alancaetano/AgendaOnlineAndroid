package agendaonline.agendaonlineapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Mensagem;
import agendaonline.agendaonlineapp.classes.Usuario;
import agendaonline.agendaonlineapp.persistencia.BancoDeDadosHelper;


public class ChatActivity extends ActionBarActivity {

    private ListView listView;
    private ImageButton btnEnviar;
    private EditText txtMsg;

    private Conversa conversa;
    private BancoDeDadosHelper bd;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        bd = new BancoDeDadosHelper(this.getApplicationContext());

        btnEnviar = (ImageButton)findViewById(R.id.button_enviar_msg);
        txtMsg = (EditText)findViewById(R.id.edittext_msg_chat);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = txtMsg.getText().toString();
                if (txt != "") {
                    Mensagem msg = new Mensagem();
                    msg.setTexto(txt);
                    msg.setId(Util.GerarID());
                    msg.setDataEnvio(new Date());
                    msg.setIdConversa(conversa.getId());
                    msg.setIdRemetente("1");// mudar para pegar das configuracoes do cel
                    bd.InserirMensagem(msg);

                    conversa.getMensagens().add(msg);
                }
            }
        });

        Bundle b = getIntent().getExtras();

        setContentView(R.layout.activity_chat);

        conversa = new Conversa();
        conversa.setId(b.getString(Conversa.COLUNA_ID));
        conversa.setIdRemetente(b.getString(Conversa.COLUNA_ID_REMETENTE));
        conversa.setNomeRemetente(b.getString(Usuario.COLUNA_NOME));

        List<Mensagem> msgs = bd.RecuperarMensagens(conversa.getId());
        conversa.setMensagens(msgs);

        this.listView = (ListView)findViewById(R.id.lista_chat);

        this.setTitle(conversa.getNomeRemetente());

        ChatListViewAdapter adapter = new ChatListViewAdapter(this, conversa);
        this.listView.setAdapter(adapter);

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
