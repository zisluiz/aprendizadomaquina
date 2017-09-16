package preprocessamento;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.reflections.Reflections;

import preprocessamento.model.Categoria;
import preprocessamento.model.CategoriaEnum;
import preprocessamento.model.Pagina;
import preprocessamento.model.Universidade;
import preprocessamento.regra.tag.RegraTag;

public class PreProcessamento {
	private final static String diretorio = "webkb/";
	private final static List<String> palavrasParaIgnorar = Arrays.asList("the", "and", "of", "to", "in", "for", "a",
			"is", "on", "no", "yes", "but", "do", "as", "if", "by", "are", "or");
	private List<CategoriaEnum> categoriasParaProcessar;
	private List<Categoria> categorias = new ArrayList<>();
	private List<RegraTag> regras;

	public void doDataMining() throws IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		File[] categorias = new File(ClassLoader.getSystemResource(diretorio).toURI()).listFiles();
		regras = findRegras();
		for (File categoria : categorias) {
			readUniversidades(categoria);
		}
	}

	public void doDataMining(List<CategoriaEnum> categoriasParaProcessar)
			throws IOException, URISyntaxException, InstantiationException, IllegalAccessException {
		this.categoriasParaProcessar = categoriasParaProcessar;
		doDataMining();
	}

	private void readUniversidades(File categoriaDirectory)
			throws IOException, InstantiationException, IllegalAccessException {
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
			throws IOException, InstantiationException, IllegalAccessException {
		File[] sites = universidadeDirectory.listFiles();

		for (File site : sites) {
			Pagina pagina = new Pagina(site.getName(), (int) site.length());
			universidade.addPagina(pagina);

			Document doc = Jsoup.parse(site, "UTF-8");
			parseFile(doc, categoria, pagina);
		}
	}

	private void parseFile(Document doc, Categoria categoria, Pagina pagina)
			throws InstantiationException, IllegalAccessException {
		Elements elements = doc.getAllElements().get(1).children();
		parseWords(categoria, pagina, elements);
		parseTitle(categoria, pagina, elements);
		parseTags(categoria, pagina, elements);
	}
	
	private void parseTitle(Categoria categoria, Pagina pagina, Elements elements) {
		String[] words = elements.select("title").text().split(" ");
		for (String word : words) {
			word = word.trim();
			word = filterWord(word);
			if (word != null) {
				categoria.addOcorrencia("!"+word);
				pagina.addOcorrencia("!"+word);
			}
		}
	}	

	private void parseWords(Categoria categoria, Pagina pagina, Elements elements) {
		String[] words = elements.text().split(" ");
		String wordAnterior = null;
		for (String word : words) {
			word = word.trim();
			word = filterWord(word);
			if (word != null) {
				categoria.addOcorrencia(word);
				pagina.addOcorrencia(word);
				
				if (wordAnterior != null && wordAnterior.equals("i") && word.equals("am")) {
					categoria.addOcorrencia("#i_am");
					pagina.addOcorrencia("#i_am");					
				}
				
				wordAnterior = word;
			}
		}
	}

	private String filterWord(String word) {
		if (!word.isEmpty()) {
			while (word.length() > 0 && (word.endsWith(".") || word.endsWith(",") || word.endsWith(":")
					|| word.endsWith("?") || word.endsWith(")") || word.endsWith("#") || word.endsWith("=")))
				word = word.substring(0, word.length() - 1);

			while (word.length() > 0 && (word.charAt(0) == '.' || word.charAt(0) == ',' || word.charAt(0) == ':'
					|| word.charAt(0) == '?' || word.charAt(0) == '(' || word.charAt(0) == '#' || word.charAt(0) == '='))
				word = word.substring(1, word.length());

			if (!word.matches(".*(\\w).*") || (word.matches("\\d*") && word.length() == 1))
				return null;

			if (!word.isEmpty())
				word = word.toLowerCase();

			if (!word.isEmpty() && !palavrasParaIgnorar.contains(word))
				return word;
		}

		return null;
	}

	private void parseTags(Categoria categoria, Pagina pagina, Elements elements)
			throws InstantiationException, IllegalAccessException {
		for (RegraTag regra : regras) {
			pagina.putOcorrencia(regra.getNome(), regra.getValor(elements, pagina));
			categoria.putOcorrencia(regra.getNome(), regra.getValor(elements, pagina));
		}
	}

	private static List<RegraTag> findRegras() throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("preprocessamento.regra.tag");
		Set<Class<? extends RegraTag>> subTypes = reflections.getSubTypesOf(RegraTag.class);
		List<RegraTag> regras = new ArrayList<>();

		for (Class<? extends RegraTag> subType : subTypes) {
			RegraTag regraTag = subType.newInstance();
			if (regraTag.ative())
				regras.add(regraTag);
		}

		return regras;
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