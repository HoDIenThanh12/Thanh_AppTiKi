package com.example.tiki.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.view.ViewProduct;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

///
// * A simple {@link Fragment} subclass.
// * Use the {@link BlankFragmentGgMap#newInstance} factory method to
// * create an instance of this fragment.
//
public class BlankFragmentGgMap extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public BlankFragmentGgMap() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment BlankFragmentGgMap.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static BlankFragmentGgMap newInstance(String param1, String param2) {
//        BlankFragmentGgMap fragment = new BlankFragmentGgMap();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    private Button btnClose;
    private View mView;
    private ViewProduct mViewProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mViewProduct= (ViewProduct) getActivity();
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_blank_gg_map, container, false);
        //initiale
        SupportMapFragment mapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.gg_Maps);
        //async map
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //when map is load
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        //when click on map
                        //initiale marrket map
                        MarkerOptions options= new MarkerOptions();
                        //set position of market
                        options.position(latLng);
                        //set title of market
                        options.title(latLng.latitude+ " : "+ latLng.longitude );
                        googleMap.clear();
                        //Animating to zoom marrket
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        googleMap.addMarker(options);
                    }
                });
            }
        });
        init();
        return mView;
    }

    private void init() {
//        btnClose= mView.findViewById(R.id.close);
//        btnClose.setOnClickListener(v->{
//            //mViewProduct.setFragmenl(false);
//            onDestroy();
//        });
    }

    @Override
    public void onDestroy() {
        Toast.makeText(mViewProduct, "destroy fragment", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}