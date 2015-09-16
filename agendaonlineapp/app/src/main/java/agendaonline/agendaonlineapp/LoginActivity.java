package agendaonline.agendaonlineapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import agendaonline.agendaonlineapp.classes.Login;


public class LoginActivity extends ActionBarActivity {

    private EditText campoLogin;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        this.campoLogin = (EditText)findViewById(R.id.edittext_login);
        this.campoSenha = (EditText)findViewById(R.id.edittext_senha);

        Button button = (Button)findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realizarLogin(campoLogin.getText().toString(), campoSenha.getText().toString());

                Intent it = new Intent(v.getContext(),PrincipalActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void realizarLogin(String login, String senha){
        Login.salvarLogin(this, campoLogin.getText().toString(), campoSenha.getText().toString());
    }
}
