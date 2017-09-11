package com.android.curriculo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	public void abrirDados(View v) {
		startActivity(new Intent(this, Dado.class));
	}

	public void abrirEndereco(View v) {
		startActivity(new Intent(this, Endereco.class));
	}

	public void abrirFormacao(View v) {
		startActivity(new Intent(this, Formacao.class));
	}

}
