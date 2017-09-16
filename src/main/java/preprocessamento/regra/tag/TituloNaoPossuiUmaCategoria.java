package preprocessamento.regra.tag;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public class TituloNaoPossuiUmaCategoria implements RegraTag {

	@Override
	public String getNome() {
		return "#tituloNaoPossuiUmaCategoria";
	}

	@Override
	public Integer getValor(Elements elements, Pagina pagina) {
		String title = elements.select("title").text();
		
		if (title.toLowerCase().contains("department") || title.toLowerCase().contains("cs") || title.toLowerCase().contains("department") || title.toLowerCase().contains("course"))
			return 1;
		else
			return 0;
	}

	@Override
	public boolean ative() {
		return false;
	}
}