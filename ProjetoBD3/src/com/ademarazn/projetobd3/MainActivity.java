package com.ademarazn.projetobd3;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ademarazn.projetobd3.dao.ContatoDAO;
import com.ademarazn.projetobd3.entidade.Contato;
import com.ademarazn.projetobd3.util.ContatoAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView mostraDados = (ListView) findViewById(R.id.list);
		TextView emptyText = (TextView) findViewById(R.id.empty);
		mostraDados.setEmptyView(emptyText);
		List<Contato> contatos = new ContatoDAO(MainActivity.this)
				.buscarContatos();
		carregarDados(contatos);

		final ContatoAdapter adapter = (ContatoAdapter) mostraDados
				.getAdapter();
		mostraDados
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(MainActivity.this,
								OpcoesActivity.class);
						intent.putExtra("id", adapter.getItem(position).getId());
						startActivity(intent);
						finish();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem myActionMenuItem = menu.findItem(R.id.pesquisar);
		final SearchView searchView = (SearchView) myActionMenuItem
				.getActionView();
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (TextUtils.isEmpty(newText)) {
					ListView listView = (ListView) findViewById(R.id.list);
					listView.clearTextFilter();
				}
				pesquisar(newText);
				return true;
			}
		});

		return true;
	}

	public void novo(MenuItem item) {
		Intent intent = new Intent(MainActivity.this, OpcoesActivity.class);
		startActivity(intent);
		finish();
	}

	public void carregarDados(List<Contato> contatos) {
		ListView mostraDados = (ListView) findViewById(R.id.list);
		ContatoAdapter adapter = new ContatoAdapter(MainActivity.this, contatos);
		mostraDados.setAdapter(adapter);
	}

	public void pesquisar(String text) {
		List<Contato> contatos = new ContatoDAO(MainActivity.this).buscar(text);
		carregarDados(contatos);
	}

}
