package com.inifap.lnmysr.parcela.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.inifap.lnmysr.parcela.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbcParcelaFragment extends Fragment {

    AlertDialog alert = null;
    LocationManager mlocManager;
    private EditText latitud, longitud;

    public AbcParcelaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_abc_parcela, container, false);

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        final EditText latitud = (EditText) rootView.findViewById(R.id.latitud);
        final EditText longitud = (EditText) rootView.findViewById(R.id.longitud);
        FloatingActionButton ubicacion = (FloatingActionButton) rootView.findViewById(R.id.ubicacion);
        /*ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xff00ff00});
        ubicacion.setBackgroundTintList(csl);*/

        //https://stackoverflow.com/questions/33865445/gps-location-provider-requires-access-fine-location-permission-for-android-6-0/33866120
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlocManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                LocationListener mlocListener = new MyLocationListener();
                //mlocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);
                mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

                Location lastLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (lastLocation == null) {
                    //Toast.makeText(getApplicationContext(), "Enciende el GPS", Toast.LENGTH_LONG).show();
                    if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        AlertNoGps();
                    }
                }else {
                    String latitudeInfo = "" + lastLocation.getLatitude();
                    String longitudeInfo = "" + lastLocation.getLongitude();

                    latitud.setText(latitudeInfo);
                    longitud.setText(longitudeInfo);

                }
            }
        });

        return rootView;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public class MyLocationListener implements LocationListener {
        @Override

        public void onLocationChanged(Location loc){

            String latitudeInfo = "" + loc.getLatitude();
            String longitudeInfo = "" + loc.getLongitude();

            latitud.setText(latitudeInfo);
            longitud.setText(longitudeInfo);

            /*String Text = "location: " +
                    "Latitud = " + loc.getLatitude() +
                    "Longitud = " + loc.getLongitude();
            tv.setText(Text);*/

        }
        public void onProviderDisabled(String provider){

            //nothin
        }


        public void onProviderEnabled(String provider){

            //nothin
        }

        public void onStatusChanged(String provider, int status, Bundle extras){
            //nothin
        }
    }/* End of Class MyLocationListener */

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

}
