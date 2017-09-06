package preprocessamento.regra;

import preprocessamento.model.Pagina;

public interface Regra {
	public String getNome();
	public String[] getPossibilidades();
	public String getValor(Pagina pagina);
	public default boolean ative() {
		return true;
	}
}
