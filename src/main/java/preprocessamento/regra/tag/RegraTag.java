package preprocessamento.regra.tag;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public interface RegraTag {
	public String getNome();
	public Integer getValor(Elements elements, Pagina pagina);
}
