package com.ademarazn.projetobd3.util;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ademarazn.projetobd3.R;
import com.ademarazn.projetobd3.entidade.Contato;
import com.amulyakhare.textdrawable.TextDrawable;

public class ContatoAdapter extends BaseAdapter {
	private Activity context;
	private List<Contato> contatos;

	public ContatoAdapter(Activity context, List<Contato> contatos) {
		this.context = context;
		this.contatos = contatos;
	}

	// retorna o tamanho da lista
	public int getCount() {
		return contatos.size();
	}

	// retorna um contato da lista
	public Contato getItem(int posicao) {
		Contato contato = contatos.get(posicao);
		return contato;
	}

	// retorna a posição de um objeto na lista
	public long getItemId(int posicao) {
		return posicao;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// recupera o contato da posicao atual
		Contato contato = contatos.get(position);

		ViewHolder holder;

		LayoutInflater inflater = LayoutInflater.from(context);

		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.mostrabanco, parent, false);
			holder = new ViewHolder(v);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		// atualiza o valor do texto para o nome do contato
		holder.textNome.setText(contato.getNome());

		// atualiza o valor do texto para o telefone do contato
		holder.textFone.setText(contato.getTelefone());

		// get first letter of each String item
		String firstLetter = String.valueOf(getItem(position).getNome()
				.toUpperCase(Locale.getDefault()).charAt(0));

		int color = contato.getColor();

		// atualiza a imagem para a imagem da letra do contato
		TextDrawable drawable = TextDrawable.builder().buildRound(firstLetter,
				color);
		holder.img.setImageDrawable(drawable);
		return v;
	}

	private class ViewHolder {
		private ImageView img;
		private TextView textNome;
		private TextView textFone;

		public ViewHolder(View v) {
			img = (ImageView) v.findViewById(R.id.img);
			textNome = (TextView) v.findViewById(R.id.nome);
			textFone = (TextView) v.findViewById(R.id.fone);
		}
	}
}
