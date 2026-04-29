package unoeste.fipp.projetoativooperante.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.projetoativooperante.entities.Erro;
import unoeste.fipp.projetoativooperante.entities.Usuario;
import unoeste.fipp.projetoativooperante.repositories.UsuarioRepository;
import unoeste.fipp.projetoativooperante.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/usuarios")
public class UsuarioRestController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> listarUsuarios(){
        List<Usuario> usuarioList = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioList);
    }

    @PostMapping
    public ResponseEntity<Object> adicionarUsuario(@RequestBody Usuario usuario){
        usuario = usuarioService.salvarUsuario(usuario);
        if(usuario != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar usuário"));
    }
}
