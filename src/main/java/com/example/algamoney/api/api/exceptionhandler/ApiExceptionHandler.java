package com.example.algamoney.api.api.exceptionhandler;

import com.example.algamoney.api.domain.exception.BusinessException;
import com.example.algamoney.api.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problem.Body> bodyList = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            bodyList.add(new Problem.Body(name, message));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateHour(OffsetDateTime.now());
        problem.setTitle("There is at least one invalid field. Review your input and try again");
        problem.setBody(bodyList);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFound( EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateHour(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateHour(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
