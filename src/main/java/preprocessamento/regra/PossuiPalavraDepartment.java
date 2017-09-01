package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraDepartment implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraDepartment";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("department") ? "s" : "n";
	}

}
