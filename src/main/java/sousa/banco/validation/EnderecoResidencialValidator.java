package sousa.banco.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sousa.banco.dto.EnderecoDTO;
import sousa.banco.enums.TipoEnderecoEnum;

import java.util.List;

public class EnderecoResidencialValidator implements ConstraintValidator<ValidaEnderecoResidencial,
        List<EnderecoDTO>> {

    @Override
    public void initialize(ValidaEnderecoResidencial constraint){}

    @Override
    public boolean isValid(List<EnderecoDTO> enderecos, ConstraintValidatorContext context) {
        if (enderecos == null) {
            return true;
        }

        long countResidencial = enderecos.stream()
            .filter(e -> e.tipoEndereco() == TipoEnderecoEnum.RESIDENCIAL)
            .count();

        return countResidencial <= 1;
    }
}
