package com.ademarazn.thebeatlesquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PerguntaActivity extends Activity {

	private int perg = 1;
	private int acertos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pergunta);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		TextView pergunta = (TextView) findViewById(R.id.pergunta);
		pergunta.setText(R.string.perg1);
		pergunta.setTypeface(FontChanger.setCircular(PerguntaActivity.this));

		RadioButton rb1 = (RadioButton) findViewById(R.id.rb_1);
		rb1.setText(R.string.perg11);
		rb1.setTypeface(FontChanger.setCircular(PerguntaActivity.this));
		RadioButton rb2 = (RadioButton) findViewById(R.id.rb_2);
		rb2.setText(R.string.perg12);
		rb2.setTypeface(FontChanger.setCircular(PerguntaActivity.this));
		RadioButton rb3 = (RadioButton) findViewById(R.id.rb_3);
		rb3.setText(R.string.perg13);
		rb3.setTypeface(FontChanger.setCircular(PerguntaActivity.this));
		RadioButton rb4 = (RadioButton) findViewById(R.id.rb_4);
		rb4.setText(R.string.perg14);
		rb4.setTypeface(FontChanger.setCircular(PerguntaActivity.this));

		((Button) findViewById(R.id.avancar)).setTypeface(FontChanger
				.setCircular(PerguntaActivity.this));
	}

	public void avancar(View v) {
		Integer resposta;
		switch (perg) {
		case 1:
			resposta = R.id.rb_1;
			break;
		case 2:
			resposta = R.id.rb_3;
			break;
		case 3:
			resposta = R.id.rb_3;
			break;
		case 4:
			resposta = R.id.rb_4;
			break;
		case 5:
			resposta = R.id.rb_2;
			break;
		case 6:
			resposta = R.id.rb_1;
			break;
		case 7:
			resposta = R.id.rb_4;
			break;
		case 8:
			resposta = R.id.rb_3;
			break;
		case 9:
			resposta = R.id.rb_1;
			break;
		case 10:
			resposta = R.id.rb_4;
			break;
		default:
			resposta = null;
			break;
		}
		RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
		if (rg.getCheckedRadioButtonId() == resposta) {
			Toast.makeText(PerguntaActivity.this, "Você acertou!",
					Toast.LENGTH_SHORT).show();
			acertos++;
		} else {
			Toast.makeText(
					PerguntaActivity.this,
					"Você errou!\nResposta correta: "
							+ ((RadioButton) findViewById(resposta)).getText(),
					Toast.LENGTH_SHORT).show();
		}
		rg.clearCheck();
		if (perg == 10) {
			perg = 1;
			atualizarPerguntas();
			Intent intent = new Intent(PerguntaActivity.this,
					AcertoActivity.class);
			intent.putExtra("acertos", String.valueOf(acertos));
			startActivity(intent);
			return;
		} else {
			perg++;
			atualizarPerguntas();
		}
	}

	private void atualizarPerguntas() {
		TextView pergunta = (TextView) findViewById(R.id.pergunta);

		RadioButton rb1 = (RadioButton) findViewById(R.id.rb_1);
		RadioButton rb2 = (RadioButton) findViewById(R.id.rb_2);
		RadioButton rb3 = (RadioButton) findViewById(R.id.rb_3);
		RadioButton rb4 = (RadioButton) findViewById(R.id.rb_4);

		switch (perg) {
		case 1:
			pergunta.setText(R.string.perg1);

			rb1.setText(R.string.perg11);
			rb2.setText(R.string.perg12);
			rb3.setText(R.string.perg13);
			rb4.setText(R.string.perg14);
			break;
		case 2:
			pergunta.setText(R.string.perg2);

			rb1.setText(R.string.perg21);
			rb2.setText(R.string.perg22);
			rb3.setText(R.string.perg23);
			rb4.setText(R.string.perg24);
			break;
		case 3:
			pergunta.setText(R.string.perg3);

			rb1.setText(R.string.perg31);
			rb2.setText(R.string.perg32);
			rb3.setText(R.string.perg33);
			rb4.setText(R.string.perg34);
			break;
		case 4:
			pergunta.setText(R.string.perg4);

			rb1.setText(R.string.perg41);
			rb2.setText(R.string.perg42);
			rb3.setText(R.string.perg43);
			rb4.setText(R.string.perg44);
			break;
		case 5:
			pergunta.setText(R.string.perg5);

			rb1.setText(R.string.perg51);
			rb2.setText(R.string.perg52);
			rb3.setText(R.string.perg53);
			rb4.setText(R.string.perg54);
			break;
		case 6:
			pergunta.setText(R.string.perg6);

			rb1.setText(R.string.perg61);
			rb2.setText(R.string.perg62);
			rb3.setText(R.string.perg63);
			rb4.setText(R.string.perg64);
			break;
		case 7:
			pergunta.setText(R.string.perg7);

			rb1.setText(R.string.perg71);
			rb2.setText(R.string.perg72);
			rb3.setText(R.string.perg73);
			rb4.setText(R.string.perg74);
			break;
		case 8:
			pergunta.setText(R.string.perg8);

			rb1.setText(R.string.perg81);
			rb2.setText(R.string.perg82);
			rb3.setText(R.string.perg83);
			rb4.setText(R.string.perg84);
			break;
		case 9:
			pergunta.setText(R.string.perg9);

			rb1.setText(R.string.perg91);
			rb2.setText(R.string.perg92);
			rb3.setText(R.string.perg93);
			rb4.setText(R.string.perg94);
			break;
		case 10:
			pergunta.setText(R.string.perg10);

			rb1.setText(R.string.perg101);
			rb2.setText(R.string.perg102);
			rb3.setText(R.string.perg103);
			rb4.setText(R.string.perg104);
			break;
		default:
			// do nothing
			break;
		}
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
