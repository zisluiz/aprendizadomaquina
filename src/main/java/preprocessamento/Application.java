package preprocessamento;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import com.opencsv.CSVWriter;

import preprocessamento.model.Categoria;
import preprocessamento.model.Ocorrencia;
import preprocessamento.model.Pagina;
import preprocessamento.model.Universidade;

public class Application {
	public static void main(String[] args) {
		try {
			PreProcessamento preProcessamento = new PreProcessamento();
			preProcessamento.doDataMining();
			//printResultsByCategoria(preProcessamento);
			//printResultsBySite(preProcessamento);
			new DataMining().generateDataMiningFile(preProcessamento);
		} catch (IOException | URISyntaxException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static void printResultsByCategoria(PreProcessamento preProcessamento) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("resultados.csv"), ';','"');
		
		int nrLinha = -1;
		boolean existeOcorrencia = false;
		do {
			String[] linha = null;
			existeOcorrencia = false;
			int numeroColunas = preProcessamento.getCategorias().size() * 2;
			linha = new String[numeroColunas];
			
			if (nrLinha == -1) {
				int nrCategoria = 1;
				for (Categoria categoria : preProcessamento.getCategorias()) {
					linha[nrCategoria-1] = categoria.getTipoCategoria().toString();
					linha[nrCategoria] = "";
					nrCategoria = nrCategoria + 2;
				}				
				
				writer.writeNext(linha);
				existeOcorrencia = true;
				nrLinha = 0;
			} else {
				int nrCategoria = 1;
				for (Categoria categoria : preProcessamento.getCategorias()) {
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
		
	     writer.close();
	}

	private static void printResultsBySite(PreProcessamento preProcessamento) throws IOException {
		
		for (Categoria categoria : preProcessamento.getCategorias()) {
			CSVWriter writer = new CSVWriter(new FileWriter("resultados-"+categoria.getTipoCategoria().toString()+".csv"), ';','"');
			
			int nrLinha = -1;
			boolean existeOcorrencia = false;
			do {
				String[] linha = null;
				existeOcorrencia = false;
				int numeroColunas = categoria.getTotalPaginas() * 2;
				linha = new String[numeroColunas];
				
				if (nrLinha == -1) {
					int nrColuna = 0;
					
					for (Universidade universidade : categoria.getUniversidades()) {
						for (Pagina pagina : universidade.getPaginas()) {
							linha[nrColuna] = pagina.getNomeArquivo();
							nrColuna++;
							linha[nrColuna] = null;
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
							
							linha[nrColuna] = ocorrencia != null ? ocorrencia.getPalavra() : null;
							nrColuna++;
							linha[nrColuna] = ocorrencia != null ? ocorrencia.getQuantidade()+"" : null;
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
}
