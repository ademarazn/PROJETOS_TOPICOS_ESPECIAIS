package com.ademarazn.projetobd2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
// para manipular os dados
// banco do android

public class MainActivity extends Activity {

	// nome do banco que será manipulado
	String nomeBanco = "cadastro";
	// variavel para manipular código SQL
	SQLiteDatabase bancoDados = null;
	EditText idPessoa;
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

		idPessoa = (EditText) findViewById(R.id.txtid);
		nomePessoa = (EditText) findViewById(R.id.txtnome);
		fonePessoa = (EditText) findViewById(R.id.txtfone);

		/*
		 * mostraDados.setOnItemClickListener(new )(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { Integer sel =
		 * mostraDados.getCheckedItemPosition(); mensagemAlerta("Telefone: " +
		 * cursor.getString(sel)); } });
		 */

		criaBanco();
		carregaDado();

		mostraDados.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (verificaRegistro()) {
					setarDados(position);
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void setarDados(int position) {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			cursor.moveToPosition(position);

			idPessoa.setText(cursor.getString(cursor.getColumnIndex("_id")));
			nomePessoa.setText(cursor.getString(cursor
					.getColumnIndex("nomepessoa")));
			fonePessoa.setText(cursor.getString(cursor
					.getColumnIndex("fonepessoa")));
		} catch (Exception e) {
			mensagemAlerta(getString(R.string.erro) + e);
		}
	}

	public void limpar() {
		idPessoa.setText("");
		nomePessoa.setText("");
		fonePessoa.setText("");
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
			// mensagemAlerta("Banco de dados criado/aberto com sucesso");
		} catch (SQLException e) {
			mensagemAlerta(getString(R.string.erro_banco) + e);
		}
	}

	public void mensagemAlerta(String msg) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// dialog.setTitle("Banco de Dados");
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
			mensagemAlerta(getString(R.string.salvo));
		} catch (Exception e) {
			mensagemAlerta(getString(R.string.erro_salvar) + e);
		}
	}

	@SuppressWarnings("deprecation")
	public void alterarBanco(int id) {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			String sql = "UPDATE tbcadastropessoa set " + "nomepessoa='"
					+ nomePessoa.getText().toString() + "'" + ", "
					+ "fonepessoa='" + fonePessoa.getText().toString() + "' "
					+ "where _id='" + id + "'";
			bancoDados.execSQL(sql);
			mensagemAlerta(getString(R.string.alterado));
		} catch (Exception e) {
			mensagemAlerta(getString(R.string.erro_alterar) + e);
		}
	}

	@SuppressWarnings("deprecation")
	public void excluirBanco(int id) {
		try {
			bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE,
					null);
			String sql = "DELETE from tbcadastropessoa where _id='" + id + "'";
			bancoDados.execSQL(sql);
			mensagemAlerta(getString(R.string.excluido));
		} catch (Exception e) {
			mensagemAlerta(getString(R.string.erro_excluir) + e);
		}
	}

	public boolean verificaRegistro() {
		String sql = "SELECT * FROM tbcadastropessoa";
		cursor = bancoDados.rawQuery(sql, null);
		return cursor.moveToFirst();
	}

	@SuppressWarnings("deprecation")
	public void carregaDado() {
		mostraDados = (ListView) findViewById(android.R.id.list);
		TextView emptyText = (TextView) findViewById(android.R.id.empty);
		mostraDados.setEmptyView(emptyText);

		verificaRegistro();
		String[] coluna = new String[] { NOMEPESSOA };
		adapterLista = new SimpleCursorAdapter(this, R.layout.mostrabanco,
				cursor, coluna, new int[] { R.id.carregaDado });
		mostraDados.setAdapter(adapterLista);
	}

	public void gravar(View v) {
		if (!nomePessoa.getText().toString().trim().isEmpty()) {
			gravarBanco();
			carregaDado();
			limpar();
		} else {
			mensagemAlerta(getString(R.string.incompleto));
		}
	}

	public void alterar(View v) {
		if (!idPessoa.getText().toString().isEmpty()) {
			alterarBanco(Integer.parseInt(idPessoa.getText().toString()));
			carregaDado();
			limpar();
		}
	}

	public void excluir(View v) {
		if (!idPessoa.getText().toString().isEmpty()) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			// dialog.setTitle("Banco de Dados");
			dialog.setMessage(getString(R.string.confirmar_excluir));
			dialog.setNegativeButton(getString(R.string.cancelar), null);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							excluirBanco(Integer.parseInt(idPessoa.getText()
									.toString()));
							carregaDado();
							limpar();
						}
					});
			dialog.show();

		}
	}
}
