package unoeste.fipp.projetoativooperante.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.entities.Erro;
import unoeste.fipp.projetoativooperante.services.DenunciaService;
import unoeste.fipp.projetoativooperante.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/denuncias")
public class DenunciaRestController {

    @Autowired
    DenunciaService denunciaService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> adicionarDenuncia(@RequestBody Denuncia denuncia){
        denuncia = denunciaService.salvarDenuncia(denuncia);
        if(denuncia != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar denúncia"));
    }

    @GetMapping
    public ResponseEntity<Object> listarDenuncias(){
        List<Denuncia> denunciaList = denunciaService.buscarTodasDenuncias();
        return ResponseEntity.ok().body(denunciaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarDenuncia(@PathVariable Long id){
        Denuncia denuncia = denunciaService.buscarPorId(id);
        return ResponseEntity.ok().body(denuncia);
    }
}
