package agendaonline.agendaonlineapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastroAlunoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastroAlunoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroAlunoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText campoNomeAluno;
    EditText campoTurma;
    EditText campoNomeProfessor;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroAlunoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroAlunoFragment newInstance(String param1, String param2) {
        CadastroAlunoFragment fragment = new CadastroAlunoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CadastroAlunoFragment() {
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        campoNomeAluno = (EditText)getView().findViewById(R.id.edittext_nome_aluno);
        campoTurma = (EditText)getView().findViewById(R.id.edittext_turma);
        campoNomeProfessor = (EditText)getView().findViewById(R.id.edittext_nome_professor);

        carregarInformacoes();
    }

    private static final String CHAVE_STORAGE_INFORMACOES_ALUNO = "INFORMACOES_ALUNO";
    private static final String CHAVE_NOME_ALUNO = "NOME_ALUNO";
    private static final String CHAVE_TURMA = "TURMA";
    private static final String CHAVE_NOME_PROFESSOR = "PROFESSOR";

    private void carregarInformacoes(){
        SharedPreferences informacoes =  this.getActivity().getSharedPreferences(CHAVE_STORAGE_INFORMACOES_ALUNO, 0);
        campoNomeAluno.setText(informacoes.getString(CHAVE_NOME_ALUNO, ""));
        campoTurma.setText(informacoes.getString(CHAVE_TURMA, ""));
        campoNomeProfessor.setText(informacoes.getString(CHAVE_NOME_PROFESSOR, ""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_aluno, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
}
