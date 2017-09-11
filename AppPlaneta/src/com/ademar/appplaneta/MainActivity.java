package com.ademar.appplaneta;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edtPeso;
	RadioGroup planetas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edtPeso = (EditText) findViewById(R.id.edtPeso);
		planetas = (RadioGroup) findViewById(R.id.radioGroup1);
	}

	public void calcular(View v) {
		try {
			int rgId = planetas.getCheckedRadioButtonId();
			double pPlaneta, pTerra;
			pTerra = Double.parseDouble(edtPeso.getText().toString());

			if (rgId == R.id.rbMercurio) {
				pPlaneta = pTerra / 10 * 0.37;
			} else if (rgId == R.id.rbVenus) {
				pPlaneta = pTerra / 10 * 0.88;
			} else if (rgId == R.id.rbMarte) {
				pPlaneta = pTerra / 10 * 0.38;
			} else if (rgId == R.id.rbJupter) {
				pPlaneta = pTerra / 10 * 2.64;
			} else if (rgId == R.id.rbSaturno) {
				pPlaneta = pTerra / 10 * 1.15;
			} else if (rgId == R.id.rbUrano) {
				pPlaneta = pTerra / 10 * 1.17;
			} else {
				pPlaneta = 0;
			}

			new AlertDialog.Builder(MainActivity.this)
					.setTitle("Peso no planeta")
					.setMessage(String.valueOf(pPlaneta))
					.setNegativeButton("OK", null).show();
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, "Preencha todos os campos",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void limpar(View v) {
		planetas.clearCheck();
		edtPeso.setText("");
	}

}
