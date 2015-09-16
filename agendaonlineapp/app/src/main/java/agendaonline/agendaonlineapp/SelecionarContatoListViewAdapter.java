package agendaonline.agendaonlineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import agendaonline.agendaonlineapp.classes.Contato;
import agendaonline.agendaonlineapp.classes.Conversa;

public class SelecionarContatoListViewAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<Contato> contatos;

    public SelecionarContatoListViewAdapter(Context context, List<Contato> contatos)
    {
        this.contatos = contatos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.celula_listview_selecionar_contato, null);
        }

        Contato contato = this.contatos.get(position);
        ((TextView) convertView.findViewById(R.id.label_contato)).setText(contato.getNome());
        return convertView;
    }
}