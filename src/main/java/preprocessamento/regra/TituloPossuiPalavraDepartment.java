package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class TituloPossuiPalavraDepartment implements Regra {

	@Override
	public String getNome() {
		return "tituloPossuiPalavraDepartment";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("!department") || pagina.getOcorrenciasDePalavras().containsKey("!dept") ? "s" : "n";
	}

}
