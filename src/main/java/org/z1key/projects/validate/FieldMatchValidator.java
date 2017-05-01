package org.z1key.projects.validate;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by User on 09.04.2017.
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch fieldMatch) {
        firstFieldName = fieldMatch.first();
        secondFieldName = fieldMatch.second();
        message = fieldMatch.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try
        {
            Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondFieldName).addConstraintViolation();
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (Exception ignore)
        {
            // ignore
        }
        return true;
    }
}
