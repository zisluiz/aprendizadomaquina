package preprocessamento.regra;

import preprocessamento.model.Pagina;
import preprocessamento.regra.tag.QuantidadeImagens;

public class PossuiMaisDe6Imagens implements Regra {
	private QuantidadeImagens quantidadeImagens = new QuantidadeImagens();
	
	@Override
	public String getNome() {
		return "possuiMaisDe6Imagens";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().get(quantidadeImagens.getNome()) > 6 ? "s" : "n";
	}

}
