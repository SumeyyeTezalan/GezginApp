package com.example.solyanmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private ImageView notEklemeSayfasinaGit;
    ListView listView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<String> notlar=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_note,container,false);
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(getLayoutInflater().getContext(),
                android.R.layout.simple_list_item_1,notlar);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("notlar");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    notlar.add( ds.getValue().toString());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        notEklemeSayfasinaGit=(ImageView) view.findViewById(R.id.not_ekle_git_imageview);
        listView=(ListView)view.findViewById(R.id.liste);
        listView.setAdapter(adapter);

        notEklemeSayfasinaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddNoteFragment fragment=new AddNoteFragment();
                FragmentManager fragmentManager=getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,fragment);
                fragmentTransaction.commit();

            }
        });
        return view;
    }
}
