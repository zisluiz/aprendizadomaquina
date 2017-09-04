package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class NomeArquivoPossuiPalavraCSMaisNumero implements Regra {

	@Override
	public String getNome() {
		return "nomeArquivoPossuiPalavraCSMaisUmNumero";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getNomeArquivo().matches(".*(cs|CS)[0-9].*") ? "s" : "n";
	}

}
