package com.ademarazn.personagem;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonagemAdapter extends BaseAdapter {
	private Context context;
	private List<PersonagemEnum> personagens;

	public PersonagemAdapter(Context context, List<PersonagemEnum> personagens) {
		this.context = context;
		this.personagens = personagens;
	}

	// retorna o tamanho da lista
	public int getCount() {
		return personagens.size();
	}

	// retorna um personagem da lista
	public Object getItem(int posicao) {
		PersonagemEnum personagem = personagens.get(posicao);
		return personagem;
	}

	// retorna a posição de um objeto na lista
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// recupera o personagem da posição atual
		PersonagemEnum personagem = personagens.get(position);

		LayoutInflater inflater = LayoutInflater.from(context);

		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.activity_main, parent, false);
		}

		// Atualiza o ImageView com a imagem do personagem.
		// A imagem é definida por um recurso no @drawable.
		ImageView img = (ImageView) v.findViewById(R.id.img);
		img.setImageResource(personagem.getImagem());

		// Atualiza o texto do TextView para o nome do personagem
		TextView txtNome = (TextView) v.findViewById(R.id.nome);
		txtNome.setText(personagem.getNome());
		TextView txtNomeJpn = (TextView) v.findViewById(R.id.nome_jpn);
		txtNomeJpn.setText(personagem.getNomeJpn());

		return v;
	}
}
