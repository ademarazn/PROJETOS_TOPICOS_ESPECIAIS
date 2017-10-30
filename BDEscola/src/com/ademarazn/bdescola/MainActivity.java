package com.ademarazn.bdescola;

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

public class MainActivity extends Activity {

	// nome do banco que será manipulado
	String nomeBanco = "bdescola";
	// variavel para manipular código SQL
	SQLiteDatabase bancoDados = null;
	EditText edtNome;
	EditText edtFone;
	EditText edtCurso;
	ListView mostraDados;
	Cursor cursor; // para manipular os dados
	// lista a ser preenchida
	SimpleCursorAdapter adapterLista;
	// campo da tabela que irá aparecer
	public static final String NOME = "nome";
	public static final String FONE = "fone";
	public static final String CURSO = "curso";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edtNome = (EditText) findViewById(R.id.edtNome);
		edtFone = (EditText) findViewById(R.id.edtFone);
		edtCurso = (EditText) findViewById(R.id.edtCurso);

		/*
		 * mostraDados.setOnItemClickListener(new )(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { Integer sel =
		 * mostraDados.getCheckedItemPosition(); mensagemAlerta("Telefone: " +
		 * cursor.getString(sel)); } });
		 */

		criaBanco();
		carregaDado();
	}

	@SuppressWarnings("deprecation")
	public void criaBanco() {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			String sql = "CREATE TABLE IF NOT EXISTS " + "tabalunos "
					+ "(_id INTEGER PRIMARY KEY, "
					+ "nome TEXT, fone TEXT, curso TEXT)";
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
			String sql = "INSERT INTO tabalunos " + "(nome,fone,curso) "
					+ "values ('" + edtNome.getText().toString() + "'" + ",'"
					+ edtFone.getText().toString() + "'" + ",'"
					+ edtCurso.getText().toString() + "')";
			bancoDados.execSQL(sql);
			mensagemAlerta("Gravado com sucesso!");
		} catch (Exception e) {
			mensagemAlerta("Erro ao gravar. " + e);
		}
	}

	public boolean verificaRegistro() {
		String sql = "SELECT * FROM tabalunos";
		cursor = bancoDados.rawQuery(sql, null);
		return cursor.moveToFirst();
	}

	@SuppressWarnings("deprecation")
	public void carregaDado() {
		mostraDados = (ListView) findViewById(R.id.listaDados);
		if (verificaRegistro()) {
			String[] coluna = new String[] { NOME, FONE, CURSO };
			adapterLista = new SimpleCursorAdapter(this, R.layout.mostrabanco,
					cursor, coluna, new int[] { R.id.nome, R.id.fone,
							R.id.curso });
			mostraDados.setAdapter(adapterLista);
		} else {
			mensagemAlerta("Não há Registros");
		}
	}

	public void salvar(View v) {
		if (!edtNome.getText().toString().trim().isEmpty()
				&& !edtFone.getText().toString().trim().isEmpty()
				&& !edtCurso.getText().toString().trim().isEmpty()) {
			gravarBanco();
			carregaDado();
		} else {
			mensagemAlerta("Há campos em branco!");
		}
	}

}
