package com.ademarazn.projetobd3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ademarazn.projetobd3.dao.ContatoDAO;
import com.ademarazn.projetobd3.entidade.Contato;
import com.ademarazn.projetobd3.util.MensagemAlerta;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class OpcoesActivity extends Activity {

	private EditText edtId;
	private EditText edtNome;
	private EditText edtFone;

	private Integer id = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes);

		edtId = (EditText) findViewById(R.id.txtid);
		edtNome = (EditText) findViewById(R.id.txtnome);
		edtFone = (EditText) findViewById(R.id.txtfone);
		Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			id = bundle.getInt("id");
			if (id != null) {
				setarDados(id);
			}
			btnSalvar.setVisibility(Button.GONE);
		} else {
			TextView id = (TextView) findViewById(R.id.id);
			Button btnAlterar = (Button) findViewById(R.id.btnAlterar);
			Button btnExcluir = (Button) findViewById(R.id.btnExcluir);
			id.setVisibility(EditText.GONE);
			edtId.setVisibility(EditText.GONE);
			btnAlterar.setVisibility(Button.GONE);
			btnExcluir.setVisibility(Button.GONE);
		}

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.ic_menu_voltar);
		// getActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	// confirmar descarte das alterações (caso tenha) antes de voltar para a
	// tela anterior
	@Override
	public void onBackPressed() {
		boolean confirma = false;
		if (id == null) {
			if (!edtNome.getText().toString().trim().isEmpty()
					|| !edtFone.getText().toString().trim().isEmpty()) {
				confirma = true;
			}
		} else {
			Contato contato = new ContatoDAO(OpcoesActivity.this)
					.buscarPorId(id);
			if (!edtNome.getText().toString().trim().equals(contato.getNome())
					|| !edtFone.getText().toString().trim()
							.equals(contato.getTelefone())) {
				confirma = true;
			}
		}
		if (confirma) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setMessage(R.string.confirmar_voltar);
			dialog.setNegativeButton(R.string.cancelar, null);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							voltar();
						}
					});
			dialog.show();
		} else {
			voltar();
		}
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setMessage(R.string.confirmar_apagar_tudo);
			dialog.setNegativeButton(R.string.cancelar, null);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (new ContatoDAO(OpcoesActivity.this)
									.excluirTudo())
								voltar();
						}
					});
			dialog.show();
			return true;
		}
		return super.onKeyLongPress(keyCode, event);
	}

	// carregar o conteúdo do contato nos EditText
	public void setarDados(int id) {
		Contato contato = new ContatoDAO(OpcoesActivity.this).buscarPorId(id);
		if (contato != null) {
			edtId.setText(contato.getId().toString());
			edtNome.setText(contato.getNome());
			edtFone.setText(contato.getTelefone());
		}
	}

	public void voltar() {
		startActivity(new Intent(OpcoesActivity.this, MainActivity.class));
		finish();
	}

	// limpar o conteúdo dos EditText
	public void limpar() {
		edtId.setText("");
		edtNome.setText("");
		edtFone.setText("");
	}

	// evento para salvar, alterar ou excluir
	public void clique(View v) {
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(edtNome.getText().toString().trim());
		contato.setTelefone(edtFone.getText().toString().trim());
		if (v.getTag().equals("SALVAR")) {
			if (!contato.getNome().toString().trim().isEmpty()) {
				ColorGenerator generator = ColorGenerator.DEFAULT;
				// generate random color
				int color = generator.getColor(contato);
				contato.setColor(color);
				if (new ContatoDAO(OpcoesActivity.this).salvar(contato))
					voltar();
			} else {
				MensagemAlerta.show(OpcoesActivity.this, R.string.incompleto);
			}
		} else if (v.getTag().equals("ALTERAR")) {
			if (contato.getId() != null) {
				final Contato contato2 = contato;
				// usando um AlertDialog para confirmar a alteração
				AlertDialog.Builder dialog = new AlertDialog.Builder(this);
				dialog.setMessage(R.string.confirmar_alterar);
				dialog.setNegativeButton(R.string.cancelar, null);
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (new ContatoDAO(OpcoesActivity.this)
										.alterar(contato2))
									voltar();
							}
						});
				dialog.show();
			}
		} else if (v.getTag().equals("EXCLUIR")) {
			if (!contato.getId().toString().isEmpty()) {
				final Contato contato2 = contato;
				// usando um AlertDialog para confirmar a exclusão
				AlertDialog.Builder dialog = new AlertDialog.Builder(this);
				dialog.setMessage(R.string.confirmar_excluir);
				dialog.setNegativeButton(R.string.cancelar, null);
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (new ContatoDAO(OpcoesActivity.this)
										.excluir(contato2))
									voltar();
							}
						});
				dialog.show();
			}
		}
	}
}
