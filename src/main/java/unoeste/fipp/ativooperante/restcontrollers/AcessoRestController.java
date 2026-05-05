package unoeste.fipp.ativooperante.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante.entities.Erro;
import unoeste.fipp.ativooperante.entities.Usuario;
import unoeste.fipp.ativooperante.services.UsuarioService;

@RestController
@RequestMapping("apis/acesso")
public class AcessoRestController {
    @Autowired
    UsuarioService usuarioService;
    // @PostMapping("logar")
    // endpoint logar

    @PostMapping("cadastrar")
    public ResponseEntity<Object> adicionar(@RequestBody Usuario usuario){
        usuario=usuarioService.inserirUsuario(usuario);
        if(usuario!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o usuário"));
    }
}
