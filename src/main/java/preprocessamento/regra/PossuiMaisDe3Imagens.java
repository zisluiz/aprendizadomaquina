package preprocessamento.regra;

import preprocessamento.model.Pagina;
import preprocessamento.regra.tag.QuantidadeImagens;

public class PossuiMaisDe3Imagens implements Regra {
	private QuantidadeImagens quantidadeImagens = new QuantidadeImagens();
	
	@Override
	public String getNome() {
		return "possuiMaisDe3Imagens";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().get(quantidadeImagens.getNome()) > 3 ? "s" : "n";
	}

}
