package agendaonline.agendaonlineapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AlanNunes on 10/07/2015.
 */
public class PrincipalPagerAdapter extends FragmentPagerAdapter{

    private static final int INDICE_AGENDAFRAGMENT_PAGERVIEW = 0;
    private static final int INDICE_CADASTROFRAGMENT_PAGERVIEW = 1;

    public PrincipalPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        if(i == INDICE_AGENDAFRAGMENT_PAGERVIEW) {
            fragment = new AgendaFragment();
            //colocar qualquer valor a ser passado como parmetro para o fragment aqui
            //args.putIntegerArrayList(chave,valor);
        }
        if(i == INDICE_CADASTROFRAGMENT_PAGERVIEW) {
            fragment = new CadastroAlunoFragment();
            //colocar qualquer valor a ser passado como parametro para o fragment aqui
            //args.putIntegerArrayList(chave,valor);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
