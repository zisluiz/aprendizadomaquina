package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraClass implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraClassOuClasses";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("class") || pagina.getOcorrenciasDePalavras().containsKey("classes") ? "s" : "n";
	}

	@Override
	public boolean ative() {
		return false;
	}
}
