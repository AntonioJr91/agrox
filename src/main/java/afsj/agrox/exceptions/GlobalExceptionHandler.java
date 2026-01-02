package afsj.agrox.exceptions;

import afsj.agrox.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorResponseDto> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
      return buildError(
              HttpStatus.NOT_FOUND,
              ex.getMessage(),
              req.getRequestURI()
      );
   }

   @ExceptionHandler(BusinessRuleViolationException.class)
   public ResponseEntity<ErrorResponseDto> handleBusinessRule(
           BusinessRuleViolationException ex,
           HttpServletRequest req) {

      return buildError(
              HttpStatus.UNPROCESSABLE_ENTITY,
              ex.getMessage(),
              req.getRequestURI()
      );
   }

   @ExceptionHandler(ConflictException.class)
   public ResponseEntity<ErrorResponseDto> handleConflict(
           ConflictException ex,
           HttpServletRequest req) {

      return buildError(
              HttpStatus.CONFLICT,
              ex.getMessage(),
              req.getRequestURI()
      );
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponseDto> handleValidation(
           MethodArgumentNotValidException ex,
           HttpServletRequest req) {

      String message = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .map(error -> error.getField() + ": " + error.getDefaultMessage())
              .distinct()
              .collect(Collectors.joining(", "));

      return buildError(
              HttpStatus.BAD_REQUEST,
              message,
              req.getRequestURI()
      );
   }

   @ExceptionHandler(DomainException.class)
   public ResponseEntity<ErrorResponseDto> handleDomain(
           DomainException ex,
           HttpServletRequest req) {

      return buildError(
              HttpStatus.UNPROCESSABLE_ENTITY,
              ex.getMessage(),
              req.getRequestURI()
      );
   }
   @ExceptionHandler(DuplicateCpfException.class)
   public ResponseEntity<ErrorResponseDto> handleDuplicateCpf(
           DuplicateCpfException ex,
           HttpServletRequest req) {

      return buildError(
              HttpStatus.CONFLICT,
              ex.getMessage(),
              req.getRequestURI()
      );
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponseDto> handleGenericException(
           Exception ex,
           HttpServletRequest req) {

      return buildError(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Internal server error",
              req.getRequestURI()
      );
   }

   private ResponseEntity<ErrorResponseDto> buildError(HttpStatus status, String message, String path) {
      ErrorResponseDto error = new ErrorResponseDto(
              Instant.now(),
              status.value(),
              status.getReasonPhrase(),
              message,
              path
      );


      return ResponseEntity.status(status).body(error);
   }
}
