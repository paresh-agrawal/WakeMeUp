package com.example.wakemeup.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakemeup.R;
import com.example.wakemeup.databinding.FragmentAlarmBinding;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment implements LocationListener {

    private FragmentAlarmBinding binding;
    RecyclerView recyclerView;
    List<Model> itemList;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    String line;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlarmViewModel alarmViewModel =
                new ViewModelProvider(this).get(AlarmViewModel.class);

        binding = FragmentAlarmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner metroStartStationSpinner = (Spinner) binding.metroStartStationSpinner;
        Spinner metroEndStationSpinner = (Spinner) binding.metroEndStationSpinner;

        populateSpinners(metroStartStationSpinner, metroEndStationSpinner);

        // Add the following lines to create RecyclerView
        recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        txtLat = root.findViewById(R.id.tv_lat_long);

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 200);
            return root;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        System.out.println("asfasfsf");
        new GetMethodDemo().execute("http://3.108.54.182:3000/api/v1/location?start=Hosahalli&destination=Mahalakshmi");



        return root;
    }

    private void populateSpinners(Spinner metroStartStationSpinner, Spinner metroEndStationSpinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.metro_stations_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        metroStartStationSpinner.setAdapter(adapter);
        metroEndStationSpinner.setAdapter(adapter);
    }

    private List<Model> initData() {

        itemList = new ArrayList<>();
        itemList.add(new Model(R.color.purple_700, line, "12 minutes", "6 stops", true));



        return itemList;
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat = binding.tvLatLong;
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}