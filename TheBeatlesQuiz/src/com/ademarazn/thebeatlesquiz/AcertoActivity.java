package com.ademarazn.thebeatlesquiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class AcertoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerto);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle pacoteDeDados = getIntent().getExtras();
		String acertos = pacoteDeDados.getString("acertos");

		TextView txtAcertos = (TextView) findViewById(R.id.acertos);
		txtAcertos.setText("Você acertou " + acertos + " perguntas!");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
