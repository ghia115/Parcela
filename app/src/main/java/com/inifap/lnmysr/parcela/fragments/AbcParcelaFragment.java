package com.inifap.lnmysr.parcela.fragments;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.inifap.lnmysr.parcela.R;
import com.inifap.lnmysr.parcela.helperDataBase.BBDD_Helper;
import com.inifap.lnmysr.parcela.helperDataBase.Estructura_BBDD;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbcParcelaFragment extends Fragment {

    private EditText parcela, latitud, longitud;
    FloatingActionButton ubicacion;
    Button updateBase;

    public AbcParcelaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_abc_parcela, container, false);
        showToolbar(getResources().getString(R.string.toolbar_tittle_abc), false);

        parcela = rootView.findViewById(R.id.parcela);
        latitud = rootView.findViewById(R.id.latitud);
        longitud = rootView.findViewById(R.id.longitud);
        ubicacion = rootView.findViewById(R.id.ubicacion);
        updateBase = rootView.findViewById(R.id.updateBase);

        updateBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BBDD_Helper helper = new BBDD_Helper(getContext());

                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                //values.put(Estructura_BBDD.ID, textoId.getText().toString());
                values.put(Estructura_BBDD.PARCELA, parcela.getText().toString());
                values.put(Estructura_BBDD.LATITUD, latitud.getText().toString());
                values.put(Estructura_BBDD.LONGITUD, longitud.getText().toString());
                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BBDD.TABLE_PARCELA, null, values);

                Toast.makeText(getActivity().getBaseContext(), "Se guardo el registro con clave: " +
                        newRowId, Toast.LENGTH_LONG).show();

                parcela.setText("");
                latitud.setText("");
                longitud.setText("");
            }
        });

        //https://stackoverflow.com/questions/33865445/gps-location-provider-requires-access-fine-location-permission-for-android-6-0/33866120
        //https://www.youtube.com/watch?v=ZgmeSK2GZDs

        //https://www.youtube.com/watch?v=QNb_3QKSmMk
        //https://github.com/miskoajkula/Gps_location/blob/master/app/src/main/java/testing/gps_location/MainActivity.java

        return rootView;
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar_abc);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        ((AppCompatActivity)getActivity()).getSupportActionBar();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 9) {

            Toast.makeText(getActivity().getApplicationContext(), "accion bar", Toast.LENGTH_LONG).show();
        }
        if (id == 9) {

            // Do something
            return true;
        }
        if (id == 0) {

            // Do something
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
