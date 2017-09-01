package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraHours implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraHours";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("hours") ? "s" : "n";
	}

}
