package preprocessamento.regra.tag;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public class QuantidadePalavras implements RegraTag {

	@Override
	public String getNome() {
		return "#qtde_palavras";
	}

	@Override
	public Integer getValor(Elements elements, Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().size();
	}

}
