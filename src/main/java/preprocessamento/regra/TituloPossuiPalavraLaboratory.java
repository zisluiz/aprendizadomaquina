package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class TituloPossuiPalavraLaboratory implements Regra {

	@Override
	public String getNome() {
		return "tituloPossuiPalavraLaboratory";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("!laboratory") ? "s" : "n";
	}

}
