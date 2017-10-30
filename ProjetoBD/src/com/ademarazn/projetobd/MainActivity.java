package com.ademarazn.projetobd;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
// para manipular os dados
// banco do android

public class MainActivity extends Activity {

	// nome do banco que será manipulado
	String nomeBanco = "cadastro";
	// variavel para manipular código SQL
	SQLiteDatabase bancoDados = null;
	EditText nomePessoa;
	EditText fonePessoa;
	ListView mostraDados;
	Cursor cursor; // para manipular os dados
	// lista a ser preenchida
	SimpleCursorAdapter adapterLista;
	// campo da tabela que irá aparecer
	public static final String NOMEPESSOA = "nomepessoa";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nomePessoa = (EditText) findViewById(R.id.txtnome);
		fonePessoa = (EditText) findViewById(R.id.txtfone);

		/*mostraDados.setOnItemClickListener(new )(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Integer sel = mostraDados.getCheckedItemPosition();
				mensagemAlerta("Telefone: " + cursor.getString(sel));
			}
		});*/

		criaBanco();
		carregaDado();
	}

	@SuppressWarnings("deprecation")
	public void criaBanco() {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			String sql = "CREATE TABLE IF NOT EXISTS " + "tbcadastropessoa "
					+ "(_id INTEGER PRIMARY KEY, "
					+ "nomepessoa TEXT, fonepessoa TEXT)";
			bancoDados.execSQL(sql);
			mensagemAlerta("Banco de dados criado/aberto com sucesso");
		} catch (SQLException e) {
			mensagemAlerta("Erro no Banco! " + e);
		}
	}

	public void mensagemAlerta(String msg) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Banco de Dados");
		dialog.setMessage(msg);
		dialog.setNegativeButton("OK", null);
		dialog.show();
	}

	@SuppressWarnings("deprecation")
	public void gravarBanco() {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			String sql = "INSERT INTO tbcadastropessoa "
					+ "(nomepessoa,fonepessoa) " + "values ('"
					+ nomePessoa.getText().toString() + "'" + ",'"
					+ fonePessoa.getText().toString() + "')";
			bancoDados.execSQL(sql);
			mensagemAlerta("Gravado com sucesso!");
		} catch (Exception e) {
			mensagemAlerta("Erro ao gravar. " + e);
		}
	}

	public boolean verificaRegistro() {
		String sql = "SELECT * FROM tbcadastropessoa";
		cursor = bancoDados.rawQuery(sql, null);
		return cursor.moveToFirst();
	}

	@SuppressWarnings("deprecation")
	public void carregaDado() {
		mostraDados = (ListView) findViewById(R.id.lvMostraDados);
		if (verificaRegistro()) {
			String[] coluna = new String[] { NOMEPESSOA };
			adapterLista = new SimpleCursorAdapter(this, R.layout.mostrabanco,
					cursor, coluna, new int[] { R.id.carregaDado });
			mostraDados.setAdapter(adapterLista);
		} else {
			mensagemAlerta("Não há Registros");
		}
	}

	public void gravar(View v) {
		if (!nomePessoa.getText().toString().trim().isEmpty()
				&& !fonePessoa.getText().toString().trim().isEmpty()) {
			gravarBanco();
			carregaDado();
		} else {
			mensagemAlerta("Há campos em branco!");
		}
	}
}
