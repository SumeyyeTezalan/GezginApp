package com.example.solyanmenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ChangeProfilFragment extends Fragment {

    private ImageView profilFotoImageview;
    private Button fotoSecButton, fotoKaydetButton;
    private int SELECT_IMAGE = 1;
    Uri imageUri;
    String userId=null;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_profile,
                container, false);

        profilFotoImageview = (ImageView) view.findViewById(R.id.profil_foto_imageview);
        fotoSecButton = (Button) view.findViewById(R.id.fotograf_sec_button);
        fotoKaydetButton = (Button) view.findViewById(R.id.fotograf_kaydet_button);
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference().child("fotograflar");

        fotoSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select picture"),
                        SELECT_IMAGE);

            }
        });

        fotoKaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().
                                getContentResolver(), data.getData());
                        profilFotoImageview.setImageBitmap(bitmap);
                        imageUri=data.getData();

                        kaydet();



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void kaydet(){
        if(imageUri!=null){
            userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference imagePath=storageReference.child(userId).
                    child(imageUri.getLastPathSegment());
            imagePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                }
            });


        }
    }
}
