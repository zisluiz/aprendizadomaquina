package preprocessamento.regra.tag;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public class TamanhoArquivo implements RegraTag {

	@Override
	public String getNome() {
		return "#tamanho_arquivo";
	}

	@Override
	public Integer getValor(Elements elements, Pagina pagina) {
		return pagina.getTamanhoArquivo();
	}
}