package co.com.bancolombia.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TestRestController {

    @GetMapping(path = "public")
    public ResponseEntity<String> open() {
        return ResponseEntity.ok("Soy una ruta pública no requiero token");
    }

    @GetMapping(path = "uwu")
    public ResponseEntity<String> closed() {
        return ResponseEntity.ok("Requiero token");
    }
}
