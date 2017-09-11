package com.example.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText ednumero1, ednumero2;
	Button btnsomar, btnlimpar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Usando a classe R para fazer  aligação de Java com XML
        ednumero1 = (EditText) findViewById(R.id.numero1);
        ednumero2 = (EditText) findViewById(R.id.numero2);
        btnsomar = (Button) findViewById(R.id.btnSomar);
        btnlimpar = (Button) findViewById(R.id.btnLimpar);
        
        //
        btnsomar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				double num1 = Double.parseDouble(ednumero1.getText().toString());
				double num2 = Double.parseDouble(ednumero2.getText().toString());
				double res = num1 + num2;
				
				AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
				dialogo.setTitle("Aviso");
				dialogo.setMessage("Soma: " + res);
				dialogo.setNeutralButton("OK", null);
				dialogo.show();
			}
		});
    }
    
  
    
}
