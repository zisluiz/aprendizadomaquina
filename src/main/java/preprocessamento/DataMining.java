package preprocessamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import preprocessamento.model.Categoria;
import preprocessamento.model.CategoriaEnum;
import preprocessamento.model.Pagina;
import preprocessamento.model.Universidade;
import preprocessamento.regra.Regra;

public class DataMining {
	public void generateDataMiningFile(PreProcessamento preProcessamento) throws InstantiationException, IllegalAccessException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("sites.arff"))) {

			writeLine(bw, "@relation sites-classification");
			writeLine(bw, "");
			List<Regra> regras = findRegras();
			
			for (Regra regra : regras)
				writeLine(bw, "@attribute ".concat(regra.getNome().concat(" {".concat(StringUtils.join(regra.getPossibilidades(), ",")).concat("}"))));
			
			writeLine(bw, "@attribute classification {".concat(StringUtils.join(CategoriaEnum.values(), ",")).concat("}"));
			writeLine(bw, "");
			writeLine(bw, "@data");
			
			for (Categoria categoria : preProcessamento.getCategorias()) {
				writeLine(bw, "%categoria ".concat(categoria.getTipoCategoria().toString()));
				
				for (Universidade universidade : categoria.getUniversidades()) {
					writeLine(bw, "%universidade ".concat(universidade.getNome()));
					
					for (Pagina pagina : universidade.getPaginas()) {
						String linha = "";
						for (Regra regra : regras)
							linha += regra.getValor(pagina).concat(",");
						
						linha += categoria.getTipoCategoria().toString();
						writeLine(bw, linha);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private static void writeLine(BufferedWriter bw, String text) throws IOException {
		bw.write(text.concat("\n"));
	}

	private static List<Regra> findRegras() throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("preprocessamento.regra");
		  Set<Class<? extends Regra>> subTypes = reflections.getSubTypesOf(Regra.class);
		  List<Regra> regras = new ArrayList<>();
		  
		  for (Class<? extends Regra> subType : subTypes) {
			  regras.add(subType.newInstance());
		  }
		  
		  return regras;
	}	
}