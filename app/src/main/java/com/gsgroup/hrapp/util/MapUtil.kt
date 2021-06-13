package com.gsgroup.hrapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentSender
import android.content.res.Resources
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.constants.Codes
import timber.log.Timber
import java.util.*


object MapUtil {

    fun isInArea(currentLatLng: LatLng, areaLatLng: LatLng, areaRadius: Double): Boolean {
        val myLoc = Location("myLoc")
        myLoc.latitude = currentLatLng.latitude
        myLoc.longitude = currentLatLng.longitude
        val areaLoc = Location("areaLoc")
        areaLoc.latitude = areaLatLng.latitude
        areaLoc.longitude = areaLatLng.longitude
        val distanceBetween = myLoc.distanceTo(areaLoc)
        Timber.e("dis: $distanceBetween meter with radius: $areaRadius")
        return distanceBetween <= areaRadius
    }

    @JvmStatic
    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(
        activity: FragmentActivity,
        fusedLocationClient: FusedLocationProviderClient?,
        includeLastKnownLocation: Boolean,
        callback: (Location) -> Unit
    ): LocationCallback {
        if (includeLastKnownLocation) requestLastKnownLocation(
            activity,
            fusedLocationClient,
            callback
        )
        Timber.e("getting location updates")
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 5000
            fastestInterval = 2500
        }
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    Timber.e("couldn't get location update")
                } else {
                    Timber.tag("location updates").i("$locationResult")
                    if (locationResult.locations.size > 0) {
                        val location = locationResult.locations[0]
                        if (location.isMockLocationEnabled(activity)) {
                            activity.showErrorDialog("ALERT!, Fake location Detected")

                        } else {
                            callback(location)
                            Timber.i("$location")
                        }
                    }
                }
            }

        }

        try {
            if (PermissionUtil.hasAllLocationPermissions(activity))
                fusedLocationClient?.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
        } catch (e: Exception) {
            Timber.e(e)
        }
        return locationCallback
    }

    @SuppressLint("MissingPermission")
    fun requestLastKnownLocation(
        fragment: FragmentActivity,
        fusedLocationClient: FusedLocationProviderClient?, callback: (Location) -> Unit
    ) {
        Timber.e("getting last known location")
        try {
            fusedLocationClient?.lastLocation?.addOnSuccessListener(fragment) { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                location?.let {
                    if (it.isMockLocationEnabled(fragment)) {
                        fragment.showErrorDialog("ALERT!, Fake location Detected")
                    } else {
                        Timber.i("$location")
                        callback(it)
                    }
                } ?: run {
                    Timber.e("couldn't fetch last know, getting updates")
                    requestLocationUpdates(fragment, fusedLocationClient, false, callback)
                }
            }?.addOnFailureListener(fragment) {
                Timber.e(it)
                requestLocationUpdates(fragment, fusedLocationClient, false, callback)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }


    fun GoogleMap.animateCameraOnMarkers(markers: ArrayList<Marker>) {
        val builder = LatLngBounds.Builder()
        //the include method will calculate the min and max bound.
        for (marker in markers) {
            builder.include(marker.position)
        }
        val bounds = builder.build()
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels
        val padding = (height * 0.15).toInt() // offset from edges of the map 20% of screen
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        animateCamera(cu)
    }
    private fun getMarkerOptions(latLng: LatLng, cursorColor: Float): MarkerOptions {
        return MarkerOptions()
            .position(latLng)
            .icon(BitmapDescriptorFactory.defaultMarker(cursorColor))
    }
    fun GoogleMap.getCurrentMarker(latLng: LatLng, zoomIn: Boolean = false): Marker {
        if (zoomIn)
            animateCameraToPosition(latLng, false)
        val markerOptions = getMarkerOptions(latLng, BitmapDescriptorFactory.HUE_RED)
        return addMarker(markerOptions)
    }

    fun GoogleMap.getAreaMarker(latLng: LatLng): Marker {
        val markerOptions = getMarkerOptions(latLng, BitmapDescriptorFactory.HUE_MAGENTA)
        return addMarker(markerOptions)
    }



    fun GoogleMap.animateCameraToPosition(
        latLng: LatLng,
        animate: Boolean
    ): GoogleMap {
        val factory = CameraUpdateFactory.newLatLngZoom(latLng, 17f)
        if (animate) animateCamera(factory) else {
            moveCamera(factory)
        }
        return this
    }

    fun Marker.drawRadiusAroundMarker(map: GoogleMap?, radius: Double): Circle? {
        return try {
            return map?.addCircle(
                CircleOptions()
                    .center(position)
                    .radius(radius)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(5f)
                    .visible(true)
                    .fillColor(Color.parseColor("#80BDBDBD"))
            )
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    fun Context.isGPSEnabled(): Boolean {
        val manager = (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


    fun FragmentActivity.openGPSDialog(isEnabledCallBack: () -> Unit) {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response: LocationSettingsResponse = task.getResult(ApiException::class.java)
                isEnabledCallBack()
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            val resolvable: ResolvableApiException =
                                exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(
                                this@openGPSDialog,
                                Codes.REQUEST_CHECK_SETTINGS
                            )
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        } catch (e: ClassCastException) {
                            // Ignore, should be an impossible error.
                        }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        showErrorDialog(
                            getString(
                                R.string.get_location_settings_failed
                            )
                        )
                }
            }
        }

    }


    @Suppress("DEPRECATION")
    fun Location.isMockLocationEnabled(context: Context): Boolean {
        // Starting with API level >= 18 we can (partially) rely on .isFromMockProvider()
        // (http://developer.android.com/reference/android/location/Location.html#isFromMockProvider%28%29)
        // For API level < 18 we have to check the Settings.Secure flag
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            !Settings.Secure.getString(context.contentResolver, Settings.Secure.ALLOW_MOCK_LOCATION)
                .equals("0")
        } else {
            isFromMockProvider
        }
    }


}