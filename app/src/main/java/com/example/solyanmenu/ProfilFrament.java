package com.example.solyanmenu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilFrament extends Fragment {



    ImageView profileUserInstagram,profilDegistirmeImageview;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View profileView = inflater.inflate(R.layout.fragment_profile,
                container,false);
        profilDegistirmeImageview=(ImageView)profileView.findViewById(R.id.profil_degistirme_imageview);
        profileUserInstagram = (ImageView) profileView.findViewById(R.id.profile_user_instagram);

        profileUserInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });

        profilDegistirmeImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeProfilFragment fragment=new ChangeProfilFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,fragment);
                fragmentTransaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return profileView;
    }

    private void openInstagram() {
        Uri instagramUri = Uri.parse("https://www.instagram.com/gezlist");
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instagramUri);



        try {
            startActivity(instagramIntent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getLayoutInflater().getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
