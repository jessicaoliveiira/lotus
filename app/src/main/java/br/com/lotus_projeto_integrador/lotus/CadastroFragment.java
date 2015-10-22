package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CadastroFragment extends Fragment {


    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //Pass your layout xml to the inflater and assign it to rootView.
        final View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        Button BtnSalvar = (Button) view.findViewById(R.id.BtnSalvar);
        BtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(getActivity(), EnderecoActivity.class);
                startActivity(intent);

            }
        });
        Button BtnCancelar = (Button) view.findViewById(R.id.BtnCancelar);

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }


}

