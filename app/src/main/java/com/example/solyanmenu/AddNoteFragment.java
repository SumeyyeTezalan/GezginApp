package com.example.solyanmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteFragment extends Fragment {

    private EditText noteEdittext;
    private Button addNoteButton,donButton;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_addnote,container,false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("notlar");

        noteEdittext=(EditText)view.findViewById(R.id.note_edittext);
        addNoteButton=(Button)view.findViewById(R.id.yeni_note_button);
        donButton=(Button)view.findViewById(R.id.not_don_button);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    databaseReference.push().setValue(noteEdittext.getText().toString());
                    Toast.makeText(getLayoutInflater().getContext(),"Başarılı",Toast.LENGTH_SHORT).show();
                    noteEdittext.setText("");

                }catch (Exception e){
                    Toast.makeText(getLayoutInflater().getContext(),"Hata Oluştu:"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }


            }
        });

        donButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesFragment fragment=new NotesFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
