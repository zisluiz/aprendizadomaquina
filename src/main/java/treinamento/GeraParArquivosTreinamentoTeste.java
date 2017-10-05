package treinamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeraParArquivosTreinamentoTeste {
	public static void main(String[] args) {
		File arff = null;
		try {
			arff = new File(ClassLoader.getSystemResource("sites.arff").toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		List<Integer> porcentagens = Arrays.asList(70, 80, 90);
		int interacoesPorPorcentagem = 5;
		
		StringBuilder sbCommom = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(arff))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.startsWith("@data")) {
					sbCommom.append(sCurrentLine+"\n");
					
					Map<String, List<String>> categoriasMisturadas = readCategorias(br);
					for (Integer porcentagem : porcentagens) {
						for (int i = 0; i < interacoesPorPorcentagem; i++) {
							categoriasMisturadas = shuffleCategorias(categoriasMisturadas);
							StringBuilder sbTreinamento = new StringBuilder();
							StringBuilder sbTeste = new StringBuilder();
							separaExemplos(categoriasMisturadas, porcentagem, sbTreinamento, sbTeste);
							escreveArquivo("train_"+porcentagem+"porc"+"_test"+(i+1)+".arff", sbCommom, sbTreinamento);
							escreveArquivo("test_"+porcentagem+"porc"+"_test"+(i+1)+".arff", sbCommom, sbTeste);
						}
					}
					break;
					
				} else {
					sbCommom.append(sCurrentLine+"\n");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void separaExemplos(Map<String, List<String>> categoriasMisturadas, Integer porcentagem,	StringBuilder sbTreinamento, StringBuilder sbTeste) {
		for (String key : categoriasMisturadas.keySet()) {
			List<String> exemplos = categoriasMisturadas.get(key);
			
			int splitIndex = (int)(exemplos.size()*(porcentagem/100.0f));
			
			List<String> exemplosTreinamento = exemplos.subList(0, splitIndex+1);
			List<String> exemplosTeste = exemplos.subList(splitIndex+1, exemplos.size());
			sbTreinamento.append("%"+key+"\n");
			sbTeste.append("%"+key+"\n");
			
			for (String exemplo : exemplosTreinamento)
				sbTreinamento.append(exemplo+"\n");
			for (String exemplo : exemplosTeste)
				sbTeste.append(exemplo+"\n");
		}
	}

	private static void escreveArquivo(String fileName, StringBuilder sbCommom, StringBuilder sbTreinamento) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

			String content = sbCommom.toString()+sbTreinamento.toString();
			bw.write(content);

			// no need to close it.
			//bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private static Map<String, List<String>> readCategorias(BufferedReader br) throws IOException {
		Map<String, List<String>> categorias = new HashMap<String, List<String>>();
		String sCurrentLine = null;
		String ultimaCategoria = null;
		
		while ((sCurrentLine = br.readLine()) != null) {
			if (sCurrentLine.startsWith("%categoria")) {
				String categoria = sCurrentLine.replace("%categoria ", "");
				categorias.put(categoria, new ArrayList<>());
				ultimaCategoria = categoria; 
			} else if (!sCurrentLine.startsWith("%")) {
				categorias.get(ultimaCategoria).add(sCurrentLine);
			}
		}		
		
		
		return categorias;
	}

	private static Map<String, List<String>> shuffleCategorias(Map<String, List<String>> categorias) {
		for (String key : categorias.keySet()) {
			List<String> exemplos = categorias.get(key);
			Collections.shuffle(exemplos);
		}
		
		return categorias;
	}
}
