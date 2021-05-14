package com.cc.viewpager101.view

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cc.viewpager101.R
import com.cc.viewpager101.databinding.FragmentCaseLocationsBinding
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.util.Constants
import com.cc.viewpager101.util.LogToConsole
import com.cc.viewpager101.viewmodel.MapFragmentViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CaseLocationsFragment : Fragment(), LocationListener {

    private lateinit var googleMap: GoogleMap

    private lateinit var locationManager: LocationManager

    //private val adapter = CaseAdapter(this)

    //private lateinit var binding : FragmentCaseLocationsBinding
    //private var mapView : MapViewInScroll? = null
    private var mapView : MapView? = null

    val viewModel by viewModels<MapFragmentViewModel>()

    private val callback = OnMapReadyCallback { googleMap ->
        LogToConsole.log("mapReady !!!")
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//        Log.d("TAG_X", "MapReady...!")
        this.googleMap = googleMap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //binding = FragmentCaseLocationsBinding.inflate(inflater, container, false)
        //return binding.root

        val view = inflater.inflate(R.layout.fragment_case_locations, container, false)


        mapView = view.findViewById(R.id.map)
        LogToConsole.log("mapView : $mapView")

        var mapViewBundle:Bundle? = null

        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(Constants.MAPVIEW_BUNDLE_KEY)
        }

        mapView?.onCreate(mapViewBundle)
        mapView?.getMapAsync(callback)

        //return inflater.inflate(R.layout.fragment_case_locations, container, false)
        //return inflater.inflate(R.layout.fragment_with_map, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*my_location.setOnClickListener {
            updateLocation(userLocation)
        }*/


        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager


        //val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        //mapFragment?.getMapAsync(callback)



        /*case_recyclerview.adapter = adapter
        val sHelper = LinearSnapHelper().also {
            it.attachToRecyclerView(case_recyclerview)
        }*/

        /*val list = mutableListOf<Case>(
            Case("The mystery of the missing shoe.", 1,LatLng(-34.0, 151.0), true),
            Case("The hound of Baskerville", 2,LatLng(-34.55, 151.0), true),
            Case("The missing charger.", 3, LatLng(-34.4, 150.0), true),
            Case("Mystery at the headquarters", 4, LatLng(33.9085, -84.4782), true)
        )

        adapter.caseList = list*/

        viewModel.getAllCases().observe(viewLifecycleOwner, Observer {
            viewModel.updateMarkers(it)
        })

        viewModel.getMarkers().observe(viewLifecycleOwner, Observer {
            googleMap.clear()
           for (option in it) {
               googleMap.addMarker(option)
           }
        })
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000L,
            5f,
            this
        )
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //mapView?.onSaveInstanceState(outState)

        var mapViewBundle : Bundle? = outState.getBundle(Constants.MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(Constants.MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView?.onSaveInstanceState(mapViewBundle)
    }

    private lateinit var userLocation: Location

    private fun updateLocation(userLocation: Location) {
        this.userLocation = userLocation
        //my_location.visibility = View.VISIBLE
        //val latLng = LatLng(userLocation.latitude, userLocation.longitude)
        //googleMap.addMarker(MarkerOptions().position(latLng).title("Sherlock"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        LogToConsole.log("MapReady ... !")
    }

    override fun onLocationChanged(p0: Location) {
        updateLocation(p0)
    }

    /*override fun openSolvedCase(case: Case) {
        Toast.makeText(requireContext(), case.caseTitle, Toast.LENGTH_SHORT).show()
        navigateToAndMark(case)
    }*/

    private fun navigateToAndMark(case: Case) {
        googleMap.clear()
        googleMap.addMarker(MarkerOptions().position(case.latLong).title(case.caseTitle))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(case.latLong))
    }
}