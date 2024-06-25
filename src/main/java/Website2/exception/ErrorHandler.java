//package Website2.exception;
//
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class ErrorHandler extends ResponseEntityExceptionHandler
//        implements AuthenticationEntryPoint, AccessDeniedHandler
//
//{
//
//    @Autowired
//    private ObjectWriter objectWriter;
//
//    // Object... args : seen as an arrays; truyen vao bn bien cung dc
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException exception,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request
//    ) {
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError fieldError : exception.getFieldErrors()) {
//            String field = fieldError.getField();
//            String message = fieldError.getDefaultMessage();
//            errors.put(field, message);
//        }
//        String message = "MethodArgumentNotValidException";
//        ErrorResponse response = new ErrorResponse(1, message, errors);
//        return  new ResponseEntity<>(response, headers, status);
//    }
//
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolation(
//            ConstraintViolationException exception
//    ) {
//        Map<String, String> errors = new HashMap<>();
//        for (ConstraintViolation<?> constraint : exception.getConstraintViolations()) {
//            String field = constraint.getPropertyPath().toString();
//            String value = constraint.getMessage();
//            errors.put(field, value);
//        }
//        String message = "ConstraintViolationException";
//        ErrorResponse response = new ErrorResponse(2, message, errors);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Object> handlerException(Exception exception){
//        String message = "Exception.";
//        ErrorResponse response = new ErrorResponse(3, exception.getMessage());
//        // log.error(message, exception);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//    @Override
//    public void commence(
//            HttpServletRequest request, HttpServletResponse response,
//            AuthenticationException exception
//    ) throws IOException {
//        String message = "AuthenticationException.";
//        ErrorResponse res = new ErrorResponse(9, exception.getMessage());
//        String json = objectWriter.writeValueAsString(res);
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write(json);
//    }
//
//    @Override
//    public void handle(
//            HttpServletRequest request, HttpServletResponse response,
//            AccessDeniedException exception
//    ) throws IOException {
//        String message = "AccessDeniedException.message";
//        ErrorResponse res = new ErrorResponse(10, message);
//        String json = objectWriter.writeValueAsString(res);
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write(json);
//    }
//
//
//}
