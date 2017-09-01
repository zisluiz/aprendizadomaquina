package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraProfessor implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraProfessor";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("professor") ? "s" : "n";
	}

}
