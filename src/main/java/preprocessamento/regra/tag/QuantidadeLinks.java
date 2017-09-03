package preprocessamento.regra.tag;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public class QuantidadeLinks implements RegraTag {

	@Override
	public String getNome() {
		return "#qtde_links";
	}

	@Override
	public Integer getValor(Elements elements, Pagina pagina) {
		return elements.select("a").size();
	}

}
