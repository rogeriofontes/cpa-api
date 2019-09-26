package br.com.unipac.cpa.constants;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;

public class Constants {

	// Auxs Constants for Controllers
	public static final String RESPONSE_UNSUCCESS = "unsuccess";

	public static final String RESPONSE_SUCCESS = "success";

	public static final String CURRENT_USER = "root@localhost";
	
	public static final String DADOS_DELETADOS = "Dados Deletados!";

	public static final String DISCIPLINES_IN_CACHE = "disciplinesInCache";

	public static final String QUESTION_IN_CACHE = "questionsInCache";

	public static final String COMPANYS_IN_CACHE = "companysInCache";

	public static final String COMPANYS_TYPES_IN_CACHE = "companysTypesInCache";

	public static final String COURSES_IN_CACHE = "coursesInCache";

	public static final String PERIODS_IN_CACHE = "periodsInCache";

	public static final String DOCUMENT_REGIONS_IN_CACHE = "documentRegionsInCache";

	public static final String LOCALS_IN_CACHE = "localsInCache";

	public static final String CHOICES_IN_CACHE = "choicesInCache";

	public static final String EVALUATIONS_IN_CACHE = "evaluationsInCache";

	public static final String PERSON_TYPES_IN_CACHE = "personTypesInCache";

	public static final String STUDENTS_IN_CACHE = "studentsInCache";

	public static final String PROFESSORS_IN_CACHE = "professorsInCache";

	public static final String TOTAL = "Total: ";

	public static final int MAXIMUM_SIZE = 100;

	private Constants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
