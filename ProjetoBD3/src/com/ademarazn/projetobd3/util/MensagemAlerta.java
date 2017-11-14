package com.ademarazn.projetobd3.util;

import android.app.AlertDialog;
import android.content.Context;

public class MensagemAlerta {

	public static void show(Context context, Object... msg) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);

		StringBuilder sb = new StringBuilder();
		for (Object object : msg) {
			try {
				Integer resId = Integer.parseInt(String.valueOf(object));
				sb.append(context.getString(resId));
			} catch (Exception e) {
				sb.append(String.valueOf(object));
			}
		}

		dialog.setMessage(sb.toString());
		dialog.setNegativeButton("OK", null);
		dialog.show();
	}

}
