package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraSyllabus implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraSyllabus";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("syllabus") ? "s" : "n";
	}

}
