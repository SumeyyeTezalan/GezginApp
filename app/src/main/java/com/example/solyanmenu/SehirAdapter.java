package com.example.solyanmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SehirAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Sehir> sehirListesi;

    public SehirAdapter(LayoutInflater layoutInflater, List<Sehir> sehirListesi) {
        this.layoutInflater = layoutInflater;
        this.sehirListesi = sehirListesi;
    }

    @Override
    public int getCount() {
        return sehirListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return sehirListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View sehirView= layoutInflater.inflate(R.layout.satir,null);
        ImageView sehirFotografi=sehirView.findViewById(R.id.sehirFotografi);
        TextView sehiradi=sehirView.findViewById(R.id.sehiradi);
        TextView sehiraciklama=sehirView.findViewById(R.id.sehiraciklama);

        sehirFotografi.setImageResource(sehirListesi.get(position).getResimId());
        sehiradi.setText(sehirListesi.get(position).getSehirAdi());
        sehiraciklama.setText(sehirListesi.get(position).getSehirAciklama());

        return sehirView;
    }
}
