package com.gimbal.hello_gimbal_android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gimbal.android.CommunicationManager;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.Visit;
import com.gimbal.android.BeaconManager;
import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconSighting;

public class MainActivity extends ActionBarActivity {

    //private PlaceManager placeManager;
    //private PlaceEventListener placeEventListener;

    private BeaconEventListener beaconEventListener;
    private BeaconManager beaconManager;
    private TextView mainText1;
    private TextView mainText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText1=(TextView) findViewById(R.id.editText);
        mainText2=(TextView) findViewById(R.id.editText2);
        Gimbal.setApiKey(this.getApplication(), "17ec8de5-fa08-47db-b399-cff8a44c64ac");
/*
        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {
                listAdapter.add(String.format("Start Visit for %s", visit.getPlace().getName()));
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onVisitEnd(Visit visit) {
                listAdapter.add(String.format("End Visit for %s", visit.getPlace().getName()));
                listAdapter.notifyDataSetChanged();
            }
        };

        placeManager = PlaceManager.getInstance();
        placeManager.addListener(placeEventListener);
        placeManager.startMonitoring();
*/

        beaconManager = new BeaconManager();
        beaconEventListener = new BeaconEventListener(){

            public void onBeaconSighting(BeaconSighting sighting) {
                mainText1.setText("We are in room 1");
                mainText2.setText(String.format("current RSSI = %d", sighting.getRSSI()));
                // This will be invoked upon beacon sighting
            }
            // add BeaconEventListener method here
        };

        beaconManager.addListener(beaconEventListener);
        beaconManager.startListening();
        CommunicationManager.getInstance().startReceivingCommunications();
    }

}
