package com.codigo.naytoe.getgo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.codigo.naytoe.getgo.databinding.ActivityMapsBinding
import com.codigo.naytoe.getgo.databinding.CustomInfoWindowBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupClickEvents()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupView() {
        binding.inputGroup.date.tvPlaceholder.text = getString(R.string.date_and_time)
        binding.inputGroup.duration.tvPlaceholder.text = getString(R.string.duration)
        binding.inputGroup.date.tvInput.text = getString(R.string.now)
        binding.inputGroup.duration.tvInput.text = getString(R.string.one_hr)
        Glide.with(this)
            .load(R.drawable.ic_icodated)
            .into(binding.inputGroup.date.ivLogo)
        Glide.with(this)
            .load(R.drawable.ic_icotime)
            .into(binding.inputGroup.duration.ivLogo)
    }

    private fun setupClickEvents() {
        binding.inputGroup.btnGo.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val yangon = LatLng(16.828744, 96.157445)
        val cameraPosition = CameraPosition.Builder()
            .target(yangon)
            .zoom(15f)
            .bearing(90f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        val marker = mMap.addMarker(MarkerOptions()
            .position(yangon)
            .icon(convertBitmapDescriptor(R.drawable.ic_baseline_arrow_drop_down_24))
        )
        marker?.showInfoWindow()

        mMap.setInfoWindowAdapter(object: GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(p0: Marker): View? {
                return null
            }

            override fun getInfoWindow(p0: Marker): View? {
                val bind = CustomInfoWindowBinding.inflate(layoutInflater)
                return bind.root
            }
        })
    }

    private fun convertBitmapDescriptor(id: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(this, id) as VectorDrawable
        val h = vectorDrawable.intrinsicHeight
        val w = vectorDrawable.intrinsicWidth
        vectorDrawable.setBounds(0, 0, w, h)
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }
}