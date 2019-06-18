package project.vendingmachine.exceptions;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import project.vendingmachine.data_model.RequestError;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler {
    private static final String STATUS_KEY = "status";

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseBody
    protected Map handleConstraintViolationException(
            javax.validation.ConstraintViolationException ex) {
        System.out.println("Exception Handler My");
        Map<Object,Object> result= new HashMap<>();
        for(ConstraintViolation error : ex.getConstraintViolations()){
            result.put(error.getPropertyPath(), error.getMessage());
        }

        result.put(STATUS_KEY, RequestError.VALIDATION_ERROR.getMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<Object> handlerExceptionResolver(
            MethodArgumentNotValidException ex) {
        System.out.println("Exception Handler My");
        Map<Object,Object> result= new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            result.put(error.getField(),error.getDefaultMessage());
        }
        for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
            try {
                Object name = getFieldValue(error.getArguments()[error.getArguments().length - 1], "resolvableString");
                result.put(name, error.getDefaultMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        result.put(STATUS_KEY, RequestError.VALIDATION_ERROR);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(RequestProcessingError.class)
    @ResponseBody
    protected ResponseEntity<Object> handleRequestProcessingError(
            RequestProcessingError ex){
        Map<String, String> result = new HashMap<>();
        result.put(STATUS_KEY, ex.getError().getMessage());
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleSqlConstraintViolationException(
            org.hibernate.exception.ConstraintViolationException ex){
        Map<String, String> result = new HashMap<>();
        result.put(STATUS_KEY, ex.getSQLException().getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    public static Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
}
