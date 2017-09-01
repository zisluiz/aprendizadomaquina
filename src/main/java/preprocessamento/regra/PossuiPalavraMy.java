package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraMy implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraMy";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("my") ? "s" : "n";
	}

}
