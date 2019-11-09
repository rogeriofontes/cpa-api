package br.com.unipac.cpa.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.unipac.cpa.exception.NotImplementationConstructionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CPFUtil {

	private CPFUtil() {
		throw new NotImplementationConstructionException("Essa classe nao pode ser instanciada");
	}

	public static boolean valida(Long documentId) {
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(String.valueOf(documentId));
			return Boolean.TRUE;
		} catch (Exception e) {
			log.info("Erro ao validar CPF: [" + e.getMessage() + "]");
			return Boolean.FALSE;
		}
	}
}
