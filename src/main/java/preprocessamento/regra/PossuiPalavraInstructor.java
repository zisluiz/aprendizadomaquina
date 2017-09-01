package preprocessamento.regra;

import preprocessamento.model.Pagina;

public class PossuiPalavraInstructor implements Regra {

	@Override
	public String getNome() {
		return "possuiPalavraInstructorOrInstructors";
	}

	@Override
	public String[] getPossibilidades() {
		return new String[] {"s", "n"};
	}

	@Override
	public String getValor(Pagina pagina) {
		return pagina.getOcorrenciasDePalavras().containsKey("instructor") || pagina.getOcorrenciasDePalavras().containsKey("instructors") ? "s" : "n";
	}

}
