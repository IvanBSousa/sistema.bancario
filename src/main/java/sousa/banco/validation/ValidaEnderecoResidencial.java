package sousa.banco.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnderecoResidencialValidator.class)
public @interface ValidaEnderecoResidencial {
    String message() default "O cliente não pode ter mais de um endereço residencial.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
