package com.ademarazn.thebeatlesquiz;

public enum Inicio implements InicioInterface {
	INICIAR {

		@Override
		public int getNome() {
			return R.string.iniciarIni;
		}

		@Override
		public int getImagem() {
			return R.drawable.ic_iniciar;
		}

	},
	SOBRE {

		@Override
		public int getNome() {
			return R.string.sobreIni;
		}

		@Override
		public int getImagem() {
			return R.drawable.ic_sobre;
		}

	},
	SAIR {

		@Override
		public int getNome() {
			return R.string.sairIni;
		}

		@Override
		public int getImagem() {
			return R.drawable.ic_sair;
		}

	}
}
