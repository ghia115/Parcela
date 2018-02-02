package com.inifap.lnmysr.parcela.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inifap.lnmysr.parcela.R;
import com.inifap.lnmysr.parcela.helperDataBase.BBDD_Helper;
import com.inifap.lnmysr.parcela.helperDataBase.Estructura_BBDD;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbcParcelaFragment extends Fragment {

    AlertDialog alert = null;
    LocationManager locationManager;
    LocationListener locationListener;
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

        parcela = (EditText) rootView.findViewById(R.id.parcela);
        latitud = (EditText) rootView.findViewById(R.id.latitud);
        longitud = (EditText) rootView.findViewById(R.id.longitud);
        ubicacion = (FloatingActionButton) rootView.findViewById(R.id.ubicacion);
        updateBase = (Button) rootView.findViewById(R.id.updateBase);

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

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        locationListener = (LocationListener) new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitud.append("\n" + location.getLatitude() + " " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return null;
        }else {
            configureButton();
        }

        //https://stackoverflow.com/questions/33865445/gps-location-provider-requires-access-fine-location-permission-for-android-6-0/33866120
        //https://www.youtube.com/watch?v=ZgmeSK2GZDs

        //https://www.youtube.com/watch?v=QNb_3QKSmMk
        //https://github.com/miskoajkula/Gps_location/blob/master/app/src/main/java/testing/gps_location/MainActivity.java

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
            }
        });
    }

}
