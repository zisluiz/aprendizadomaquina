package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraMember implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraMember";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("member") ? "s" : "n";
	}

}
