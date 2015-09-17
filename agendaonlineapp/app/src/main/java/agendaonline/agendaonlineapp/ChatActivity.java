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
