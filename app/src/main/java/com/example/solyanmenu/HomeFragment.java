package com.example.solyanmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListView listView;
    List<Sehir> sehirListesi=new ArrayList<>();
    SehirAdapter sehirAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home,container,false);

       //home Fragment
       listView=(ListView)view.findViewById(R.id.listview);
       sehirListesi.add(new Sehir(R.drawable.foto1,"Mardin","Güneydoğuda gidilecek güzel bir şehir"));
       sehirListesi.add(new Sehir(R.drawable.foto2,"Adana","Akdenizde gidilecek güzel bir şehir"));
       sehirListesi.add(new Sehir(R.drawable.foto3,"İzmir","Egede gidilecek güzel bir şehir"));
       sehirListesi.add(new Sehir(R.drawable.foto4,"İstanbul","Marmarada gidilecek güzel bir şehir"));

       sehirAdapter=new SehirAdapter(getLayoutInflater(),sehirListesi);
       listView.setAdapter(sehirAdapter);


        return view;
    }
}
