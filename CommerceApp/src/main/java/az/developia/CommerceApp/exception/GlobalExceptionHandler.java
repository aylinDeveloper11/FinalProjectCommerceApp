package az.developia.CommerceApp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleResponseStatus(ResponseStatusException ex) {
		return ResponseEntity.status(ex.getStatusCode()).body(Map.of("timestamp", LocalDateTime.now(), "status",
				ex.getStatusCode().toString(), "error", ex.getStatusCode().toString(), "message", ex.getReason()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("timestam", LocalDateTime.now(), "status", 400,
				"error", "Bad Request", "message", ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(Map.of("timestamp", LocalDateTime.now(), "status", 400, "error",
				"Validation Failed", "message", errors));
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<?> handleViolationException(ConstraintViolationException ex){
//		
//		return ResponseEntity.badRequest().body(Map.of("timestamp", LocalDateTime.now(), "status",
//				400, "error", "Validation Failed", "message", ex.getMessage()));	
//	}

}