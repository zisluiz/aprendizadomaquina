package preprocessamento.regra;

import preprocessamento.model.Pagina;
import preprocessamento.regra.tag.TamanhoArquivo;

public class TamanhoArquivoMaiorQue5000Bytes implements Regra {
	private TamanhoArquivo tamanhoArquivo = new TamanhoArquivo();
	
	@Override
	public String getNome() {
		return "tamanhoArquivoMaiorQue5000Bytes";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().get(tamanhoArquivo.getNome()) > 5000 ? "s" : "n";
	}

}
