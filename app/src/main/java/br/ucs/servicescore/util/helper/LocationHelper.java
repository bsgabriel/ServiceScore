package br.ucs.servicescore.util.helper;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.location.LocationManager.GPS_PROVIDER;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper {
    private final Context context;
    private final LocationManager locationManager;
    private final LocationListener locationListener;
    private Runnable onLocationChange;

    public LocationHelper(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListenerImpl();
    }

    public void setOnLocationChange(Runnable onLocationChange) {
        this.onLocationChange = onLocationChange;
    }

    private String getCityFromCoordinates(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (!addresses.isEmpty()) {
                return addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkLocationPermission() {
        boolean fineLocation = checkSelfPermission(context, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED;
        boolean coarseLocation = checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED;
        return fineLocation && coarseLocation;
    }

    private void requestLocationPermission() {
        if (!checkLocationPermission()) {
            return;
        }
        locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, locationListener);
    }

    public void pedirPermissao() {
        requestPermissions((Activity) context, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void handlePermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
            requestLocationPermission();
        }
    }

    private Location getLastKnownLocation() {
        if (!checkLocationPermission()) {
            return null;
        }
        return locationManager.getLastKnownLocation(GPS_PROVIDER);
    }

    public String getCity() {
        Location location = getLastKnownLocation();
        if (location != null)
            return getCityFromCoordinates(location.getLatitude(), location.getLongitude());

        return "";
    }

    public void stopLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }

    private class LocationListenerImpl implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (onLocationChange != null) {
                onLocationChange.run();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }
}
