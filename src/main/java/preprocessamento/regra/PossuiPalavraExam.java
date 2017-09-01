package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraExam implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraExam";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("exam") ? "s" : "n";
	}

}
