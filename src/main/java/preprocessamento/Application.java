package preprocessamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import com.opencsv.CSVWriter;

import preprocessamento.model.Categoria;
import preprocessamento.model.CategoriaEnum;
import preprocessamento.model.Ocorrencia;
import preprocessamento.model.Pagina;
import preprocessamento.model.Universidade;
import preprocessamento.regra.Regra;

public class Application {
	public static void main(String[] args) {
		try {
			DataMining dataMining = new PreProcessamento().doDataMining();
			printResultsByCategoria(dataMining);
			printResultsBySite(dataMining);
			generateDataMiningFile(dataMining);
		} catch (IOException | URISyntaxException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static void printResultsByCategoria(DataMining dataMining) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("resultados.csv"), ';','"');
		
		int nrLinha = -1;
		boolean existeOcorrencia = false;
		do {
			String[] linha = null;
			existeOcorrencia = false;
			int numeroColunas = dataMining.getCategorias().size() * 2;
			linha = new String[numeroColunas];
			
			if (nrLinha == -1) {
				int nrCategoria = 1;
				for (Categoria categoria : dataMining.getCategorias()) {
					linha[nrCategoria-1] = categoria.getTipoCategoria().toString();
					linha[nrCategoria] = "";
					nrCategoria = nrCategoria + 2;
				}				
				
				writer.writeNext(linha);
				existeOcorrencia = true;
				nrLinha = 0;
			} else {
				int nrCategoria = 1;
				for (Categoria categoria : dataMining.getCategorias()) {
					Ocorrencia ocorrencia = null;
					if (categoria.getOcorrenciasDePalavras().size() > nrLinha) {
						ocorrencia = categoria.getOcorrencias().get(nrLinha);
						existeOcorrencia = true;
					}
					
					if (ocorrencia != null && ocorrencia.getQuantidade() > 5) {
						linha[nrCategoria-1] = ocorrencia != null ? ocorrencia.getPalavra() : "";
						linha[nrCategoria] = ocorrencia != null ? ocorrencia.getQuantidade()+"" : "";
					}
					nrCategoria = nrCategoria + 2;
				}
				
				if (existeOcorrencia) {
					writer.writeNext(linha);
					nrLinha++;
				}
			}
			
			
		} while (existeOcorrencia);
		
		
	     
		/*for (Categoria categoria : dataMining.getCategorias()) {
			writer.writeNext(new String[] {categoria.getTipoCategoria().toString()});
			
			Set<String> wordsSet = categoria.getOcorrenciasDePalavras().keySet();
			String[] words = wordsSet.toArray(new String[wordsSet.size()]); 
			writer.writeNext(words);
			
			String[] ocorrencias = new String[words.length];
			for (int i = 0; i < words.length; i++) {
				ocorrencias[i] = categoria.getOcorrenciasDePalavras().get(words[i]).toString();
			}
		
			writer.writeNext(ocorrencias);
		}*/
		
	     
	     writer.close();
	}

	private static void printResultsBySite(DataMining dataMining) throws IOException {
		
		for (Categoria categoria : dataMining.getCategorias()) {
			CSVWriter writer = new CSVWriter(new FileWriter("resultados-"+categoria.getTipoCategoria().toString()+".csv"), ';','"');
			
			int nrLinha = -1;
			boolean existeOcorrencia = false;
			do {
				String[] linha = null;
				existeOcorrencia = false;
				int numeroPaginas = dataMining.getTotalPaginas();
				int numeroColunas = dataMining.getCategorias().size() + (numeroPaginas * 2);
				linha = new String[numeroColunas];
				
				if (nrLinha == -1) {
					int nrColuna = 0;
					
					for (Universidade universidade : categoria.getUniversidades()) {
						for (Pagina pagina : universidade.getPaginas()) {
							linha[nrColuna] = pagina.getNomeArquivo();
							nrColuna++;
							linha[nrColuna] = "";
							nrColuna++;												
						}
					}
					
					writer.writeNext(linha);
					existeOcorrencia = true;
					nrLinha = 0;
				} else {
					int nrColuna = 0;
					
					for (Universidade universidade : categoria.getUniversidades()) {
						for (Pagina pagina : universidade.getPaginas()) {
							Ocorrencia ocorrencia = null;
							
							if (pagina.getOcorrenciasDePalavras().size() > nrLinha) {
								ocorrencia = pagina.getOcorrencias().get(nrLinha);
								existeOcorrencia = true;
							}
							
							linha[nrColuna] = ocorrencia != null ? ocorrencia.getPalavra() : "";
							nrColuna++;
							linha[nrColuna] = ocorrencia != null ? ocorrencia.getQuantidade()+"" : "";
							nrColuna++;												
							
						}
					}
					
					if (existeOcorrencia) {
						writer.writeNext(linha);
						nrLinha++;
					}
				}
				
				
			} while (existeOcorrencia);
			
		     writer.close();
		}
	}
	
	private static void generateDataMiningFile(DataMining dataMining) throws InstantiationException, IllegalAccessException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("sites.arff"))) {

			writeLine(bw, "@relation sites-classification");
			writeLine(bw, "");
			List<Regra> regras = findRegras();
			
			for (Regra regra : regras)
				writeLine(bw, "@attribute ".concat(regra.getNome().concat(" {".concat(StringUtils.join(regra.getPossibilidades(), ",")).concat("}"))));
			
			writeLine(bw, "@attribute classification {".concat(StringUtils.join(CategoriaEnum.values(), ",")).concat("}"));
			writeLine(bw, "");
			writeLine(bw, "@data");
			
			for (Categoria categoria : dataMining.getCategorias()) {
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
