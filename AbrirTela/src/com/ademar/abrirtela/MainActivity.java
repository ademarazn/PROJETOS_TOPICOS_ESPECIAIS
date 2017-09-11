package com.ademar.abrirtela;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void abrirTela(View v) {
		startActivity(new Intent(this, Tela2.class));
	}
	
	public void sair(View v) {
		finish();
	}

}
