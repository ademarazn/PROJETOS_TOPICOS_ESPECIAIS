package com.example.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void calcular(View v) {
		double salario, novosal = 0;
		EditText edsalario = (EditText) findViewById(R.id.edsalario);
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgopcoes);
		int op = rg.getCheckedRadioButtonId();

		if (edsalario.getText().length() > 0 && op != RadioGroup.NO_ID) {
			salario = Double.parseDouble(edsalario.getText().toString());
			if (op == R.id.rd40) {
				novosal = salario * 1.4;
			} else if (op == R.id.rd45) {
				novosal = salario * 1.45;
			} else if (op == R.id.rd50) {
				novosal = salario * 1.5;
			}

			new AlertDialog.Builder(MainActivity.this).setTitle("Novo salário")
					.setMessage("Meu novo salário é: " + novosal)
					.setNeutralButton("OK", null).show();
		}
	}

	public void limpar(View v) {
		EditText edsalario = (EditText) findViewById(R.id.edsalario);
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgopcoes);
		edsalario.setText("");
		rg.clearCheck();
	}
}
