package com.desafio.gerenciadorclientes_api.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.desafio.gerenciadorclientes_api.core.validation.ValidacaoException;
import com.desafio.gerenciadorclientes_api.domain.exception.EntidadeNaoEncontradaException;
import com.desafio.gerenciadorclientes_api.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import jakarta.annotation.Nullable;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  
  private static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
          + "Tente novamente e se o problema persistir, entre em contato "
          + "com o administrador do sistema.";

  @Autowired      
  private MessageSource messageSource;

@ExceptionHandler({ ValidacaoException.class })
public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), 
                        HttpStatus.BAD_REQUEST, request);
}

private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
	HttpStatus status, WebRequest request) {
        
    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
    
    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
            .map(objectError -> {
                String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                
                String name = objectError.getObjectName();
                
                if (objectError instanceof FieldError) {
                    name = ((FieldError) objectError).getField();
                }
                
                return Problem.Object.builder()
                    .name(name)
                    .userMessage(message)
                    .build();
            })
            .collect(Collectors.toList());
    
    Problem problem = createProblemBuilder(status, problemType, detail)
        .userMessage(detail)
        .objects(problemObjects)
        .build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
}

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
   return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
  }
 
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, 
        HttpHeaders headers, HttpStatus status, WebRequest request) {
    
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", 
            ex.getRequestURL());
    
    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)        
            .build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }  

	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch(
					(MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
	
		return super.handleTypeMismatch(ex, headers, status, request);
	}

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

      ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

      String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
          + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
          ex.getName(), ex.getValue(), ex.getRequiredType());

      Problem problem = createProblemBuilder(status, problemType, detail)
              .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
              .build();
  
      return handleExceptionInternal(ex, problem, headers, status, request);
  }

  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

      Throwable rootCause = ExceptionUtils.unwrapInvocationTargetException(e);
      if(rootCause instanceof InvalidFormatException) {
        return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
      } else if(rootCause instanceof PropertyBindingException) {
        return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
      }

      ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
      String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
  
      Problem problem = createProblemBuilder(status, problemType, detail)
              .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
              .build();
      
      return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
  }
  
  private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException e, HttpHeaders headers,
  HttpStatus status, WebRequest request) {

    String path = joinPath(e.getPath());

    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' não existe. " +
    "Corrija ou remova essa propriedade e tente novamente.", path);

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(e, problem, headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, HttpHeaders headers,
  HttpStatus status, WebRequest request) {

    String path = joinPath(e.getPath());

    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido." 
    + " Corrija e informe um valor compatível com o tipo %s", path, e.getValue(), e.getTargetType().getSimpleName());

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(e, problem, headers, status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = e.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();
    
    return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
  }
  
  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.ERRO_NEGOCIO;
    String detail = e.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

    return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
  }
  
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if(body == null) {
      body = Problem.builder()
              .timestamp(OffsetDateTime.now())
              .title(status.getReasonPhrase())
              .status(status.value())
              .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
              .build();
    } else if (body instanceof String) {
      body = Problem.builder()
              .timestamp(OffsetDateTime.now())
              .title((String) body)
              .status(status.value())
              .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
              .build();
    }
           
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

    return Problem.builder()
          .timestamp(OffsetDateTime.now())
          .status(status.value())
          .type(problemType.getUri())
          .title(problemType.getTitle())
          .detail(detail);
  }

  private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
}
