package preprocessamento;

import java.util.ArrayList;
import java.util.List;

import preprocessamento.model.Categoria;
import preprocessamento.model.Universidade;

public class DataMining {
	private List<Categoria> categorias = new ArrayList<>();

	public void add(Categoria categoria) {
		categorias.add(categoria);
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public int getTotalPaginas() {
		int totalPaginas = 0;
		for (Categoria categoria : categorias)
			for (Universidade universidade : categoria.getUniversidades())
				totalPaginas += universidade.getPaginas().size();
		
		return totalPaginas;
	}
}