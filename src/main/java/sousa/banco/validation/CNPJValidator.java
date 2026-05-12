package sousa.banco.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNPJValidator implements ConstraintValidator<ValidaCNPJ, String> {

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int soma = 0;
        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso1[i];
        }

        int digito1 = soma % 11;
        digito1 = (digito1 < 2) ? 0 : 11 - digito1;

        soma = 0;
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso2[i];
        }

        int digito2 = soma % 11;
        digito2 = (digito2 < 2) ? 0 : 11 - digito2;

        return digito1 == (cnpj.charAt(12) - '0') &&
                digito2 == (cnpj.charAt(13) - '0');
    }
}
