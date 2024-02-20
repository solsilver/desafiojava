package br.com.banco.controller.exception;

import br.com.banco.service.exception.DataInvalida;
import br.com.banco.service.exception.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static br.com.banco.service.ultil.DateUtil.datenow;


@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StantardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StantardError error = new StantardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), datenow());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DataInvalida.class)
    public ResponseEntity<StantardError> dataInvalida (DataInvalida e, HttpServletRequest request) {
        StantardError error = new StantardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), datenow());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}