package preprocessamento;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import preprocessamento.model.Categoria;
import preprocessamento.model.CategoriaEnum;
import preprocessamento.model.Pagina;
import preprocessamento.model.Universidade;

public class PreProcessamento {
	private final static String diretorio = "webkb/";
	private final static List<String> palavrasParaIgnorar = Arrays.asList("the", "and", "of", "to", "in", "for", "a",
			"is", "the", "on", ".", ",", ":", "no", "yes", "but", "any", "do", "of", "is", "[", "]", "a", "as", "if",
			"by", "are", "|", "/");
	private List<CategoriaEnum> categoriasParaProcessar;
	private List<Categoria> categorias = new ArrayList<>();

	public void doDataMining() throws IOException, URISyntaxException {
		File[] categorias = new File(ClassLoader.getSystemResource(diretorio).toURI()).listFiles();

		for (File categoria : categorias) {
			readUniversidades(categoria);
		}
	}

	public void doDataMining(List<CategoriaEnum> categoriasParaProcessar) throws IOException, URISyntaxException {
		this.categoriasParaProcessar = categoriasParaProcessar;
		doDataMining();
	}

	private void readUniversidades(File categoriaDirectory) throws IOException {
		CategoriaEnum categoriaEnum = CategoriaEnum.valueOf(categoriaDirectory.getName());
		if (categoriasParaProcessar == null || categoriasParaProcessar.contains(categoriaEnum)) {
			Categoria categoria = new Categoria(categoriaEnum);
			add(categoria);

			File[] universidades = categoriaDirectory.listFiles();

			for (File universidadeDirectory : universidades) {
				Universidade universidade = new Universidade(universidadeDirectory.getName());
				categoria.addUniversidade(universidade);
				readSites(universidadeDirectory, categoria, universidade);
			}
		}
	}

	private void readSites(File universidadeDirectory, Categoria categoria, Universidade universidade)
			throws IOException {
		File[] sites = universidadeDirectory.listFiles();

		for (File site : sites) {
			Pagina pagina = new Pagina(site.getName());
			universidade.addPagina(pagina);

			Document doc = Jsoup.parse(site, "UTF-8");
			parseFile(doc, categoria, pagina);
		}
	}

	private void parseFile(Document doc, Categoria categoria, Pagina pagina) {
		Elements elements = doc.getAllElements().get(1).children();
		String[] words = elements.text().split(" ");

		for (String word : words) {
			word = word.trim();
			word = filterWord(word);
			if (word != null) {
				categoria.addOcorrencia(word);
				pagina.addOcorrencia(word);
			}
		}
	}

	private String filterWord(String word) {
		if (!word.isEmpty()) {
			while (word.length() > 0 && (word.endsWith(".") || word.endsWith(",") || word.endsWith(":")
					|| word.endsWith("?") || word.endsWith(")")))
				word = word.substring(0, word.length() - 1);

			while (word.length() > 0 && (word.charAt(0) == '.' || word.charAt(0) == ',' || word.charAt(0) == ':'
					|| word.charAt(0) == '?' || word.charAt(0) == '('))
				word = word.substring(1, word.length());
			
			if (!word.matches(".*(\\w).*") || (word.matches("\\d*") && word.length() == 1))
				word = "";

			if (!word.isEmpty())
				word = word.toLowerCase();
			
			if (!palavrasParaIgnorar.contains(word))
				return word;
		}

		return null;
	}
	
	public void add(Categoria categoria) {
		categorias.add(categoria);
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public int getTotalPaginas() {
		int totalPaginas = 0;
		for (Categoria categoria : categorias)
			totalPaginas += categoria.getTotalPaginas();
		
		return totalPaginas;
	}	
	
}