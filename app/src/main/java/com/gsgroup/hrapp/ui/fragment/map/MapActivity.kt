package com.gsgroup.hrapp.ui.fragment.map

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseActivity
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.ActivityMapBinding

class MapActivity : BaseActivity<ActivityMapBinding, MapViewModel>(), OnMapReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {

    }

    override val mViewModel: MapViewModel by viewModels()

}