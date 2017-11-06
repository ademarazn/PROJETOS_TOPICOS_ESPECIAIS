package com.ademarazn.bdescola2;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

public class VisualizarActivity extends ListActivity {

	// nome do banco que será manipulado
	String nomeBanco = "bdescola";
	// variavel para manipular código SQL
	SQLiteDatabase bancoDados = null;
	Cursor cursor; // para manipular os dados

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (abrirBanco())
			setListAdapter(carregaDado());
		else
			setListAdapter(null);
	}

	@SuppressWarnings("deprecation")
	public boolean abrirBanco() {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			return true;
		} catch (SQLException e) {
			mensagemAlerta("Erro no Banco! " + e);
			return false;
		}
	}

	public boolean verificaRegistro() {
		String sql = "SELECT * FROM tabalunos";
		cursor = bancoDados.rawQuery(sql, null);
		return cursor.moveToFirst();
	}

	@SuppressWarnings("deprecation")
	public SimpleCursorAdapter carregaDado() {
		if (verificaRegistro()) {
			String[] coluna = new String[] { MainActivity.NOME,
					MainActivity.FONE, MainActivity.CURSO };
			return new SimpleCursorAdapter(this, R.layout.mostrabanco, cursor,
					coluna, new int[] { R.id.nome, R.id.fone, R.id.curso });
		} else {
			mensagemAlerta("Não há Registros");
		}
		return null;
	}

	public void mensagemAlerta(String msg) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Banco de Dados");
		dialog.setMessage(msg);
		dialog.setNegativeButton("OK", null);
		dialog.show();
	}

	@Override
	public void onBackPressed() {
		bancoDados.close();
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualizar, menu);
		return true;
	}

}
