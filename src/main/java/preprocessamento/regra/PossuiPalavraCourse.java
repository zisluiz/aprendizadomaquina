package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraCourse implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraCourseOuCourses";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("course") || pagina.getOcorrenciasDePalavras().containsKey("courses") ? "s" : "n";
	}

}
