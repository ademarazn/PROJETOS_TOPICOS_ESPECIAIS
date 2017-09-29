package com.ademarazn.personagem;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends Activity {

	private TextToSpeech speech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

		// Alterando informações da ActionBar
		getActionBar().setTitle(getString(MainActivity.personagem.getNome()));
		getActionBar().setSubtitle(MainActivity.personagem.getNomeJpn());
		getActionBar().setIcon(MainActivity.personagem.getImagem());

		// Habilitando o botão voltar na ActionBar
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Pegando da view o ImageView img2 e alterando a imagem
		ImageView img = (ImageView) findViewById(R.id.img2);
		img.setImageResource(MainActivity.personagem.getImagem());

		// Pegando da view o TextView nome2 e alterando o texto
		TextView txtNome = (TextView) findViewById(R.id.nome2);
		txtNome.setText(MainActivity.personagem.getNome());

		// Pegando da view o TextView nome_jpn2 e alterando o texto
		TextView txtNomeJpn = (TextView) findViewById(R.id.nome_jpn2);
		txtNomeJpn.setText(MainActivity.personagem.getNomeJpn());

		// Pegando da view o TextView desc e alterando o texto
		TextView txtDesc = (TextView) findViewById(R.id.desc);
		txtDesc.setText(MainActivity.personagem.getDesc());

		// Nova instância da classe TextToSpeech
		// que serve para converter texto em voz
		speech = new TextToSpeech(InfoActivity.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						if (status == TextToSpeech.SUCCESS) {
							if (Locale.getDefault().getCountry().equals("BR")) {
								speech.setLanguage(Locale.getDefault());
							} else {
								speech.setLanguage(Locale.ENGLISH);
							}
						}
					}
				});

	}

	@Override
	protected void onStop() {
		super.onStop();
		speech.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		// Id correspondente ao botão Up/Home da ActionBar
		case android.R.id.home:
			// Voltar para a activity anterior / ParentActivity
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_about:
			// nova instância de um AlertDialog
			new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
					.setTitle(R.string.app_name).setMessage(R.string.about)
					.setNeutralButton("OK", null).show();
			return true;
		case R.id.action_play:
			// Enviando textos para serem convertidos em voz e reproduzidos
			speech.speak(getString(MainActivity.personagem.getNome()),
					TextToSpeech.QUEUE_FLUSH, null);
			speech.speak(getString(MainActivity.personagem.getDesc()),
					TextToSpeech.QUEUE_ADD, null);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
