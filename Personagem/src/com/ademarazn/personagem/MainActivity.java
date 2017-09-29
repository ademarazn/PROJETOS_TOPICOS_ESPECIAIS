package com.ademarazn.personagem;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	public static PersonagemEnum personagem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Alterando o subtítulo na ActionBar
		getActionBar().setSubtitle(R.string.dbz_jpn);

		// ArrayList com os itens que serão inseridos na ListActivity
		// pelo setListAdapter com o uso da classe PersonagemAdapter
		ArrayList<PersonagemEnum> list = new ArrayList<PersonagemEnum>();

		// Adicionando os itens na lista
		list.add(PersonagemEnum.GOKU);
		list.add(PersonagemEnum.CHICHI);
		list.add(PersonagemEnum.VEGETA);
		list.add(PersonagemEnum.BULMA);
		list.add(PersonagemEnum.GOHAN);
		list.add(PersonagemEnum.TRUNKS);
		list.add(PersonagemEnum.GOTEN);
		list.add(PersonagemEnum.BILLS);
		list.add(PersonagemEnum.WHIS);
		list.add(PersonagemEnum.FREEZA);
		list.add(PersonagemEnum.SAIR);

		// Adaptador de lista customizado para cada linha
		setListAdapter(new PersonagemAdapter(this, list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			// Criando um AlertDialog
			new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
					.setTitle(R.string.app_name).setMessage(R.string.about)
					.setNeutralButton("OK", null).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Pega o Personagem naquela posição
		personagem = (PersonagemEnum) this.getListAdapter().getItem(position);

		if (personagem.getNome() == R.string.sair) {
			// Fecha o aplicativo
			finish();
		} else {
			// Chamando a InfoActivity que conterá as informações do Personagem
			startActivity(new Intent(MainActivity.this, InfoActivity.class));
		}
	}
}
