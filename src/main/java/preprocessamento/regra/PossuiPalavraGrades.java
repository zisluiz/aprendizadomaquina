package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraGrades implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraGradeOuGrades";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("grade") || pagina.getOcorrenciasDePalavras().containsKey("grades") ? "s" : "n";
	}

}
