package co.edu.uceva.ovaservice.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;
@FeignClient(name = "curso-service")
public interface ICursoClient {
    @GetMapping("/cursos")
    ResponseEntity<Map<String, Object>> getCursos() ;
}
