package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraCourses implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraCourses";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("courses") ? "s" : "n";
	}

}
