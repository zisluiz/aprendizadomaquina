package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraFaculty implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraFaculty";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("faculty") ? "s" : "n";
	}

}
