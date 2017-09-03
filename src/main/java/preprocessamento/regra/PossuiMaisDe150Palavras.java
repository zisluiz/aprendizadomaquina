package preprocessamento.regra;

import preprocessamento.model.Pagina;
import preprocessamento.regra.tag.QuantidadePalavras;

public class PossuiMaisDe150Palavras implements Regra {
	private QuantidadePalavras quantidadePalavras = new QuantidadePalavras();
	
	@Override
	public String getNome() {
		return "possuiMaisDe150Palavras";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().get(quantidadePalavras.getNome()) > 150 ? "s" : "n";
	}

}
