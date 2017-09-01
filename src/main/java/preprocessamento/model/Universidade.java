package preprocessamento.model;

import java.util.ArrayList;
import java.util.List;

public class Universidade {
	private String nome;
	private List<Pagina> paginas = new ArrayList<>();
	
	public Universidade(String nome) {
		this.nome = nome;
	}

	public void addPagina(Pagina pagina) {
		paginas.add(pagina);
	}
	
	public List<Pagina> getPaginas() {
		return paginas;
	}
	
	public String getNome() {
		return nome;
	}
}
