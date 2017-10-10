package com.ademarazn.thebeatlesquiz;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InicioAdapter extends BaseAdapter {
	private Context context;
	private List<Inicio> list;

	public InicioAdapter(Context context, List<Inicio> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Inicio inicio = list.get(position);

		LayoutInflater inflater = LayoutInflater.from(context);

		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.activity_main, parent, false);
		}

		ImageView img = (ImageView) v.findViewById(R.id.img);
		img.setImageResource(inicio.getImagem());

		switch (inicio) {
		case INICIAR:
			img.setColorFilter(v.getResources().getColor(
					android.R.color.holo_green_dark));
			break;
		case SOBRE:
			img.setColorFilter(v.getResources().getColor(
					android.R.color.holo_blue_dark));
			break;
		case SAIR:
			img.setColorFilter(v.getResources().getColor(
					android.R.color.holo_red_light));
			break;
		default:
			break;
		}

		TextView txt = (TextView) v.findViewById(R.id.txt);
		txt.setText(inicio.getNome());
		txt.setTypeface(FontChanger.setCircular(v.getContext()));

		return v;
	}

}
