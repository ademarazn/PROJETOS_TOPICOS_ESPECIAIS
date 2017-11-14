package com.ademarazn.projetobd3.dao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ademarazn.projetobd3.R;
import com.ademarazn.projetobd3.entidade.Contato;
import com.ademarazn.projetobd3.util.MensagemAlerta;

public class ContatoDAO {

	// nome do banco que será manipulado
	private String nomeBanco = "cadastro";
	// variavel para manipular código SQL
	private SQLiteDatabase bancoDados = null;

	private Context context;

	public ContatoDAO(Context context) {
		this.context = context;
		criarTabela();
	}

	@SuppressWarnings("deprecation")
	private void openOrCreateDatabase() {
		try {
			bancoDados = context.openOrCreateDatabase(nomeBanco,
					Activity.MODE_WORLD_READABLE, null);
		} catch (SQLException e) {
			MensagemAlerta.show(context, R.string.erro_banco, e);
		}
	}

	private void closeDatabase() {
		bancoDados.close();
	}

	public void criarTabela() {
		openOrCreateDatabase();
		String sql = "CREATE TABLE IF NOT EXISTS tbcadastropessoa "
				+ "(_id INTEGER PRIMARY KEY, "
				+ "nome TEXT, fone TEXT, cor INTEGER)";
		bancoDados.execSQL(sql);
		closeDatabase();
	}

	public List<Contato> buscarContatos() {
		openOrCreateDatabase(); // abre o banco de dados
		Cursor cursor = bancoDados.rawQuery("SELECT * FROM tbcadastropessoa "
				+ "ORDER BY upper(nome) asc, fone asc", null);
		List<Contato> contatos = cursorToEntityList(cursor);
		closeDatabase(); // fecha o banco de dados
		return contatos;
	}

	public Contato buscarPorId(int id) {
		openOrCreateDatabase();
		String sql = "SELECT * FROM tbcadastropessoa WHERE _id='" + id + "' "
				+ "ORDER BY upper(nome) asc, fone asc";
		Cursor cursor = bancoDados.rawQuery(sql, null);
		Contato contato = cursorToEntity(cursor);
		closeDatabase();
		return contato;
	}

	public List<Contato> buscar(String pesquisa) {
		openOrCreateDatabase();
		String sql = "SELECT * FROM tbcadastropessoa WHERE nome LIKE '"
				+ pesquisa + "%' OR fone LIKE '" + pesquisa + "%' "
				+ "ORDER BY upper(nome) asc, fone asc";
		Cursor cursor = bancoDados.rawQuery(sql, null);
		List<Contato> contatos = cursorToEntityList(cursor);
		closeDatabase();
		return contatos;
	}

	private Contato cursorToEntity(Cursor cursor) {

		if (cursor.moveToFirst()) {
			Contato contato = new Contato();
			contato.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
			contato.setTelefone(cursor.getString(cursor.getColumnIndex("fone")));
			contato.setColor(cursor.getInt(cursor.getColumnIndex("cor")));
			return contato;
		}
		return null;
	}

	private List<Contato> cursorToEntityList(Cursor cursor) {
		List<Contato> contatos = new ArrayList<Contato>();
		while (cursor.moveToNext()) {
			Contato contato = new Contato();
			contato.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
			contato.setTelefone(cursor.getString(cursor.getColumnIndex("fone")));
			contato.setColor(cursor.getInt(cursor.getColumnIndex("cor")));
			contatos.add(contato);
		}
		return contatos;
	}

	public boolean salvar(Contato contato) {
		boolean sucesso = true;
		try {
			openOrCreateDatabase();
			String sql = "INSERT INTO tbcadastropessoa " + "(nome, fone, cor) "
					+ "values ('" + contato.getNome() + "','"
					+ contato.getTelefone() + "','" + contato.getColor() + "')";
			bancoDados.execSQL(sql);
			Toast.makeText(context, R.string.salvo, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			sucesso = false;
			MensagemAlerta.show(context, R.string.erro_salvar, e);
		} finally {
			closeDatabase();
		}
		return sucesso;
	}

	public boolean alterar(Contato contato) {
		boolean sucesso = true;
		try {
			openOrCreateDatabase();
			String sql = "UPDATE tbcadastropessoa set " + "nome='"
					+ contato.getNome() + "'" + ", " + "fone='"
					+ contato.getTelefone() + "' " + "where _id='"
					+ contato.getId() + "'";
			bancoDados.execSQL(sql);
			Toast.makeText(context, R.string.alterado, Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			sucesso = false;
			MensagemAlerta.show(context, R.string.erro_alterar, e);
		} finally {
			closeDatabase();
		}
		return sucesso;
	}

	public boolean excluir(Contato contato) {
		boolean sucesso = true;
		try {
			openOrCreateDatabase();
			String sql = "DELETE from tbcadastropessoa where _id='"
					+ contato.getId() + "'";
			bancoDados.execSQL(sql);
			Toast.makeText(context, R.string.excluido, Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			sucesso = false;
			MensagemAlerta.show(context, R.string.erro_excluir, e);
		} finally {
			closeDatabase();
		}
		return sucesso;
	}

	public boolean excluirTudo() {
		boolean sucesso = true;
		try {
			openOrCreateDatabase();
			String sql = "DELETE from tbcadastropessoa";
			bancoDados.execSQL(sql);
			Toast.makeText(context, R.string.tudo_excluido, Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			sucesso = false;
			MensagemAlerta.show(context, R.string.erro_excluir, e);
		} finally {
			closeDatabase();
		}
		return sucesso;
	}
}
