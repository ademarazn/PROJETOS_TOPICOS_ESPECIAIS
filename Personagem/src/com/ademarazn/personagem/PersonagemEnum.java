package com.ademarazn.personagem;

import java.io.Serializable;

public enum PersonagemEnum implements PersonagemInterface, Serializable {
	GOKU {
		@Override
		public int getNome() {
			return R.string.kakarotto;
		}

		@Override
		public int getNomeJpn() {
			return R.string.kakarotto_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.kakarotto_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.goku;
		}

	},
	CHICHI {
		@Override
		public int getNome() {
			return R.string.chichi;
		}

		@Override
		public int getNomeJpn() {
			return R.string.chichi_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.chichi_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.chichi;
		}
	},
	VEGETA {
		@Override
		public int getNome() {
			return R.string.vegeta;
		}

		@Override
		public int getNomeJpn() {
			return R.string.vegeta_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.vegeta_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.vegeta;
		}
	},
	BULMA {
		@Override
		public int getNome() {
			return R.string.bulma;
		}

		@Override
		public int getNomeJpn() {
			return R.string.bulma_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.bulma_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.bulma;
		}
	},
	GOHAN {
		@Override
		public int getNome() {
			return R.string.gohan;
		}

		@Override
		public int getNomeJpn() {
			return R.string.gohan_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.gohan_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.gohan;
		}
	},
	TRUNKS {
		@Override
		public int getNome() {
			return R.string.trunks;
		}

		@Override
		public int getNomeJpn() {
			return R.string.trunks_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.trunks_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.trunks;
		}
	},
	GOTEN {
		@Override
		public int getNome() {
			return R.string.goten;
		}

		@Override
		public int getNomeJpn() {
			return R.string.goten_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.goten_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.goten;
		}
	},
	BILLS {
		@Override
		public int getNome() {
			return R.string.bills;
		}

		@Override
		public int getNomeJpn() {
			return R.string.bills_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.bills_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.bills;
		}
	},
	WHIS {
		@Override
		public int getNome() {
			return R.string.whis;
		}

		@Override
		public int getNomeJpn() {
			return R.string.whis_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.whis_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.whis;
		}
	},
	FREEZA {
		@Override
		public int getNome() {
			return R.string.freeza;
		}

		@Override
		public int getNomeJpn() {
			return R.string.freeza_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.freeza_desc;
		}

		@Override
		public int getImagem() {
			return R.drawable.freeza;
		}
	},
	SAIR {
		@Override
		public int getNome() {
			return R.string.sair;
		}

		@Override
		public int getNomeJpn() {
			return R.string.sair_jpn;
		};

		@Override
		public int getDesc() {
			return R.string.none;
		}

		@Override
		public int getImagem() {
			return R.drawable.sair;
		}
	}
}
