package pe.senac.br.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pe.senac.br.backend.dto.LoginRequest;
import pe.senac.br.backend.dto.LoginResponse;
import pe.senac.br.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")   // <— libera só esse front-end
public class AuthController {
	
	@Configuration
	public class CorsConfig implements WebMvcConfigurer {
	    
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**")
	                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:5500", "http://localhost:5500", "*")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowedHeaders("*")
	                .allowCredentials(false);
	    }
	}
	
  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    boolean ok = authService.autenticar(req.getUsername(), req.getSenha());
    if (ok) {
      return ResponseEntity.ok(new LoginResponse(true, "Login bem-sucedido"));
    } else {
      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new LoginResponse(false, "Credenciais inválidas"));
    }
  }
}