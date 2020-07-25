package com.poke.ui.map

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.poke.R
import com.poke.common.BaseActivity
import com.poke.common.component.BindViewModelComponent
import com.poke.databinding.ActivityMapsBinding
import com.poke.ui.main.model.PokemonModel

class MapsActivity : BaseActivity<ActivityMapsBinding>(R.layout.activity_maps), OnMapReadyCallback,
    BindViewModelComponent {

    private val viewModel by viewModels<MapViewModel>()

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setupObserve()
    }

    override fun bindViewModel() {
        binding.vm = viewModel.apply {
            setUpPokemonModel(
                (intent.extras?.get("pokemonModel") as? PokemonModel) ?: PokemonModel()
            )
        }
    }

    override fun setupObserve() {

        viewModel.pokemonModel.observe(this, Observer {
            for (PokemonLocationModel in it.locationList) {
                val location = LatLng(PokemonLocationModel.lat, PokemonLocationModel.lng)
                map.addMarker(
                    MarkerOptions().position(location).title(it.filterName)
                )
                map.moveCamera(CameraUpdateFactory.newLatLng(location))
            }
        })
    }
}