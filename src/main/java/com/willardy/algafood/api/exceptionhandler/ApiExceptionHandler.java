package com.willardy.algafood.api.exceptionhandler;

import com.willardy.algafood.domain.exception.EntidadeEmUsoException;
import com.willardy.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.willardy.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .status(status.value())
                    .title((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return  Problem.builder()
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .status(status.value())
                .detail(detail);
    }
}
