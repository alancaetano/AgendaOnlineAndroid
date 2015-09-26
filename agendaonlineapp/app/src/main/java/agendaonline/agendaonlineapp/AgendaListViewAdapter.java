package agendaonline.agendaonlineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;

public class AgendaListViewAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<Conversa> conversas;

    public AgendaListViewAdapter(Context context, List<Conversa> conversas)
    {
        this.conversas = conversas;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return this.conversas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.conversas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.celula_listview_agenda, null);
        }

        Conversa conversa = this.conversas.get(position);

        ((TextView) convertView.findViewById(R.id.label_contato)).setText(conversa.getNomeRemetente());
        //((TextView) convertView.findViewById(R.id.label_mensagem)).setText(conversa.getMensagens().get(conversa.getMensagens().size() - 1).getMensagem());

        return convertView;
    }
}