package com.ademarazn.thebeatlesquiz;

import android.content.Context;
import android.graphics.Typeface;

public class FontChanger {
	public static Typeface setCircular(Context context) {
		return Typeface.createFromAsset(context.getAssets(),
				"fonts/circular.ttf");
	}
}
