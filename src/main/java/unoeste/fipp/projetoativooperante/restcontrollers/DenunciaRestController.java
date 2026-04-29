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

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarDenuncia(@PathVariable Long id){
        Denuncia denuncia = denunciaService.buscarPorId(id);
        return ResponseEntity.ok().body(denuncia);
    }

    @GetMapping
    public ResponseEntity<Object> listarDenuncias(){
        List<Denuncia> denunciaList = denunciaService.listarTodasDenuncias();
        return ResponseEntity.ok().body(denunciaList);
    }

    @PostMapping
    public ResponseEntity<Object> adicionarDenuncia(@RequestBody Denuncia denuncia){
        denuncia = denunciaService.inserirDenuncia(denuncia);
        if(denuncia != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar denúncia"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removerDenuncia(@PathVariable Long id){
        if(denunciaService.apagarDenuncia(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o denuncia"));
    }
}
