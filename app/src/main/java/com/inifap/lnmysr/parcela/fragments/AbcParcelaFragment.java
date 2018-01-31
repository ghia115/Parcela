package com.inifap.lnmysr.parcela.fragments;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inifap.lnmysr.parcela.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbcParcelaFragment extends Fragment {


    public AbcParcelaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_abc_parcela, container, false);

        FloatingActionButton ubicacion = (FloatingActionButton) rootView.findViewById(R.id.ubicacion);
        /*ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xff00ff00});
        ubicacion.setBackgroundTintList(csl);


        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return rootView;
    }

}
