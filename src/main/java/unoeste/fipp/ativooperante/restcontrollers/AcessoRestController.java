package unoeste.fipp.ativooperante.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante.entities.Erro;
import unoeste.fipp.ativooperante.entities.Usuario;
import unoeste.fipp.ativooperante.security.JWTTokenProvider;
import unoeste.fipp.ativooperante.services.UsuarioService;

@RestController
@RequestMapping("apis/acesso")
public class AcessoRestController {
    @Autowired
    UsuarioService usuarioService;
    // @PostMapping("logar")
    // endpoint logar

    @PostMapping("/autenticar-adm")
    public ResponseEntity<Object> autenticarAdm(String login, String senha, String nivel)
    {
        String token="";
        if (login.equals("admin@pm.br") && senha.equals("123321")) {
            token = JWTTokenProvider.getToken(login, nivel);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("ACESSO NAO PERMITIDO", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/autenticar-usuario")
    public ResponseEntity<Object> autenticarUser(String login, String senha, String nivel)
    {
        String token="";
        if (login.equalsIgnoreCase(login) && senha.equals(senha)) {
            token = JWTTokenProvider.getToken(login, nivel);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("ACESSO NAO PERMITIDO", HttpStatus.NOT_ACCEPTABLE);
    }
}
