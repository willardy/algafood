package com.willardy.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidador, ConstraintValidatorContext context) {
        boolean valido = true;

        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidador.getClass(), this.valorField).getReadMethod().invoke(objetoValidador);
            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidador.getClass(), this.descricaoField).getReadMethod().invoke(objetoValidador);

            if(valor != null && descricao != null && BigDecimal.ZERO.compareTo(valor) == 0){
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
