package agendaonline.agendaonlineapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Usuario;
import agendaonline.agendaonlineapp.persistencia.BancoDeDadosHelper;


public class SelecionarContatoActivity extends ActionBarActivity {

    private ListView listView;
    private List<Usuario> contatos;
    private BancoDeDadosHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new BancoDeDadosHelper(this.getApplicationContext());
        setContentView(R.layout.activity_selecionar_contato);

        this.listView = (ListView)findViewById(R.id.lista_contatos);

        this.contatos = bd.RecuperarUsuarios();
        SelecionarContatoListViewAdapter adapter = new SelecionarContatoListViewAdapter(this, contatos);
        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = contatos.get(position);

                String idConversa = Util.GerarID();
                Conversa conv = new Conversa();
                conv.setId(idConversa);
                conv.setIdRemetente(usuario.getId());
                bd.InserirConversa(conv);

                Intent it = new Intent(view.getContext(), ChatActivity.class);
                Bundle b = new Bundle();
                b.putString(Conversa.COLUNA_ID, idConversa );
                b.putString(Conversa.COLUNA_ID_REMETENTE, usuario.getId());
                b.putString(Usuario.COLUNA_NOME, usuario.getNome());
                it.putExtras(b);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selecionar_contato, menu);
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
