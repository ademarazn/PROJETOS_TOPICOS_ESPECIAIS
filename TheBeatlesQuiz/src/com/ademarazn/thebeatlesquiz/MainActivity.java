package com.ademarazn.thebeatlesquiz;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
try{
		ArrayList<Inicio> list = new ArrayList<Inicio>();
		list.add(Inicio.INICIAR);
		list.add(Inicio.SOBRE);
		list.add(Inicio.SAIR);

		setListAdapter(new InicioAdapter(this, list));
}catch(Exception e) {
	System.err.println("erro: " + e.getMessage());
}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Inicio inicio = (Inicio) this.getListAdapter().getItem(position);

		switch (inicio) {
		case INICIAR:
			startActivity(new Intent(MainActivity.this, PerguntaActivity.class));
			break;
		case SOBRE:
			startActivity(new Intent(MainActivity.this, SobreActivity.class));
			break;
		case SAIR:
			finish();
			break;
		default:
			// do nothing
			break;
		}
	}

}
