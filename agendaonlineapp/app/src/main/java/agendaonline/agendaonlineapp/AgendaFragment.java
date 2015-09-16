package agendaonline.agendaonlineapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import agendaonline.agendaonlineapp.classes.Conversa;
import agendaonline.agendaonlineapp.classes.Mensagem;
import agendaonline.agendaonlineapp.classes.Usuario;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton buttonNovaConversa;
    private ListView listView;
    private List<Conversa> conversas;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public AgendaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.buttonNovaConversa = (ImageButton)this.getActivity().findViewById(R.id.button_nova_conversa);

        this.listView = (ListView)this.getActivity().findViewById(R.id.list);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(view.getContext(), ChatActivity.class);
                Conversa conversa = conversas.get(position);
                Bundle b = new Bundle();
                b.putString(Conversa.COLUNA_ID, conversa.getId());
                b.putString(Conversa.COLUNA_NOME_REMETENTE, conversa.getNomeRemetente());
                it.putExtras(b);
                startActivity(it);
            }
        });

        this.buttonNovaConversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), ChatActivity.class);
                Conversa conversa = new Conversa();
                Bundle b = new Bundle();
                b.putString(Conversa.COLUNA_ID, conversa.getId());
                b.putString(Conversa.COLUNA_NOME_REMETENTE, conversa.getNomeRemetente());
                it.putExtras(b);
                startActivity(it);
            }
        });

        Conversa c = new Conversa();
        c.setNomeRemetente("professor");

        Mensagem msg = new Mensagem();
        msg.setNomeRemetente("professor");
        msg.setDataEnvio(new java.util.Date());
        msg.setTexto("mensagem do professor...");

        Mensagem msg2 = new Mensagem();
        msg2.setNomeRemetente("pai");
        msg2.setDataEnvio(new java.util.Date());
        msg2.setTexto("mensagem do pai...");

        c.setMensagens(new ArrayList<Mensagem>());
        c.getMensagens().add(msg);
        c.getMensagens().add(msg2);

        conversas = new ArrayList<Conversa>();
        conversas.add(c);
        AgendaListViewAdapter adapter = new AgendaListViewAdapter(this.getActivity(), conversas);
        this.listView = (ListView)getView().findViewById(R.id.list);
        this.listView.setAdapter(adapter);
    }
}
