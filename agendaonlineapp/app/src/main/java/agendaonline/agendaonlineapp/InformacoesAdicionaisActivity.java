package agendaonline.agendaonlineapp;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InformacoesAdicionaisActivity extends ActionBarActivity {

    private EditText campoObservacoesAluno;
    private EditText campoNomeResponsavel;
    private EditText campoTelefoneResponsavel;
    private EditText campoContatosEmergencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_adicionais);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowTitleEnabled(false);
        final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
        View view = inflater.inflate(R.layout.action_bar_edit_mode, null);
        ab.setCustomView(view,new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        ab.setDisplayShowCustomEnabled(true);

        Button botaoSalvar = (Button)findViewById(R.id.action_bar_button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                carregarCadastro();
                Toast.makeText(getApplicationContext(), "Atualizado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });;

        Button botaoCancelar = (Button)findViewById(R.id.action_bar_button_cancelar);
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });;

        campoObservacoesAluno = (EditText)findViewById(R.id.edittext_observacoes_aluno);
        campoNomeResponsavel = (EditText)findViewById(R.id.edittext_nome_responsavel);
        campoTelefoneResponsavel = (EditText)findViewById(R.id.edittext_telefone_responsavel);
        campoContatosEmergencia = (EditText)findViewById(R.id.edittext_contatos_emergenciais);

        carregarCadastro();
    }

    private static final String CHAVE_STORAGE_INFORMACOES_ADICIONAIS = "STORAGE_INFORMACOES_ADICIONAIS";
    private static final String CHAVE_OBSERVACOES_DO_ALUNO = "OBSERVACOES_DO_ALUNO";
    private static final String CHAVE_NOME_DO_RESPONSAVEL = "NOME_DO_RESPONSAVEL";
    private static final String CHAVE_TELEFONE_DO_RESPONSAVEL = "TELEFONE_DO_RESPONSAVEL";
    private static final String CHAVE_CONTATOS_DE_EMERGENCIA = "CONTATOS_DE_EMERGENCIA";

    private void carregarCadastro(){
        SharedPreferences informacoesAdicionais = getApplicationContext().getSharedPreferences(CHAVE_STORAGE_INFORMACOES_ADICIONAIS, MODE_PRIVATE);
        campoObservacoesAluno.setText(informacoesAdicionais.getString(CHAVE_OBSERVACOES_DO_ALUNO, ""));
        campoNomeResponsavel.setText(informacoesAdicionais.getString(CHAVE_NOME_DO_RESPONSAVEL, ""));
        campoTelefoneResponsavel.setText(informacoesAdicionais.getString(CHAVE_TELEFONE_DO_RESPONSAVEL, ""));
        campoContatosEmergencia.setText(informacoesAdicionais.getString(CHAVE_CONTATOS_DE_EMERGENCIA, ""));
    }

    private void salvarCadastro(){
        SharedPreferences informacoesAdicionais = getApplicationContext().getSharedPreferences(CHAVE_STORAGE_INFORMACOES_ADICIONAIS, MODE_PRIVATE);
        SharedPreferences.Editor edit = informacoesAdicionais.edit();
        edit.clear();
        edit.putString(CHAVE_OBSERVACOES_DO_ALUNO, campoObservacoesAluno.getText().toString());
        edit.putString(CHAVE_NOME_DO_RESPONSAVEL, campoNomeResponsavel.getText().toString());
        edit.putString(CHAVE_TELEFONE_DO_RESPONSAVEL, campoTelefoneResponsavel.getText().toString());
        edit.putString(CHAVE_CONTATOS_DE_EMERGENCIA, campoContatosEmergencia.getText().toString());
        edit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacoes_adicionais, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
