package preprocessamento.regra;

import preprocessamento.model.Pagina;
import preprocessamento.regra.tag.QuantidadeLinks;

public class PossuiMaisDe22Links implements Regra {
	private QuantidadeLinks quantidadeLinks = new QuantidadeLinks();
	
	@Override
	public String getNome() {
		return "possuiMaisDe22Links";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().get(quantidadeLinks.getNome()) > 22 ? "s" : "n";
	}

}
