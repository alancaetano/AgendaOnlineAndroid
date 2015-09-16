package agendaonline.agendaonlineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Mensagem;

public class ChatListViewAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private Conversa conversa;

    public ChatListViewAdapter(Context context, Conversa conversa)
    {
            this.conversa = conversa;
            mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.conversa.getMensagens().size();
    }

    @Override
    public Object getItem(int position) {
        return this.conversa.getMensagens().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.celula_listview_chat, null);
        }

        Mensagem msg = conversa.getMensagens().get(position);

        String data = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(msg.getDataEnvio());

        ((TextView) convertView.findViewById(R.id.label_hora)).setText(data);
        ((TextView) convertView.findViewById(R.id.label_mensagem)).setText(msg.getTexto());

        if(msg.getNomeRemetente() == conversa.getNomeRemetente())
        {
            convertView.setBackgroundResource(R.drawable.balaoremetente);
            convertView.setPadding(50,10,10,0);
        }
        else
        {
            convertView.setBackgroundResource(R.drawable.balaousuario);
            convertView.setPadding(10,10,50,0);
        }

        return convertView;
    }
}