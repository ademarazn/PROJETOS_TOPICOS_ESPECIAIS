package com.ademarazn.thebeatlesquiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;

public class SobreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sobre);

		// Alterando a fonte
		TextView sobre = (TextView) findViewById(R.id.txt_sobre);
		sobre.setTypeface(FontChanger.setCircular(SobreActivity.this));
		TextClock ano = (TextClock) findViewById(R.id.ano);
		ano.setTypeface(FontChanger.setCircular(SobreActivity.this));

		getActionBar().setDisplayHomeAsUpEnabled(true);
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
