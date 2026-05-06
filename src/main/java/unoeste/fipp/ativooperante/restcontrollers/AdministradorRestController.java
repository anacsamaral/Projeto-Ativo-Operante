package unoeste.fipp.ativooperante.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
//import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante.entities.*;
import unoeste.fipp.ativooperante.repositories.DenunciaRepository;
import unoeste.fipp.ativooperante.security.JWTTokenProvider;
import unoeste.fipp.ativooperante.services.DenunciaService;
import unoeste.fipp.ativooperante.services.OrgaoService;
import unoeste.fipp.ativooperante.services.TipoService;

import java.util.List;

@RestController
@RequestMapping("apis/adm")
public class AdministradorRestController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    TipoService tipoService;
    @Autowired
    OrgaoService orgaoService;
    @Autowired
    DenunciaService denunciaService;
    @Autowired
    private DenunciaRepository denunciaRepository;

    // ------------------------ CRUD - TIPO DE PROBLEMA ------------------------------ //

    @PostMapping("/adicionar-tipo")
    public ResponseEntity<Object> adicionarTipo(@RequestBody Tipo tipo){
        tipo = tipoService.inserirTipo(tipo);
        if(tipo != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o tipo de problema"));
    }

    @GetMapping("/listar-tipos")
    public ResponseEntity<Object> buscarTipos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    @PutMapping("/alterar-tipo")
    public ResponseEntity<Object> atualizarTipo(@RequestBody Tipo tipo){
        tipo=tipoService.inserirTipo(tipo);
        if(tipo!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o tipo de problema"));
    }

    @DeleteMapping("/excluir-tipo/{id}")
    public ResponseEntity<Object> removerTipo(@PathVariable Long id){
        if(tipoService.apagarTipo(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o tipo de problema"));
    }

    // ------------------------ CRUD - ÓRGAO COMPETENTE ------------------------------ //

    @PostMapping("/adicionar-orgao")
    public ResponseEntity<Object> adicionarOrgao(@RequestBody Orgao orgao){
        orgao = orgaoService.inserirOrgao(orgao);
        if(orgao != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o órgão competente"));
    }

    @GetMapping("/listar-orgaos")
    public ResponseEntity<Object> buscarOrgaos(){
//        String token=request.getHeader("Authorization");
//        if (!JWTTokenProvider.verifyToken(token))
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Orgao> orgaoList=orgaoService.listarTodosOrgaos();
        return ResponseEntity.ok(orgaoList);
    }

    @PutMapping("/alterar-orgao")
    public ResponseEntity<Object> atualizarOrgao(@RequestBody Orgao orgao){
        orgao=orgaoService.inserirOrgao(orgao);
        if(orgao!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o órgão competente"));
    }

    @DeleteMapping("/excluir-orgao/{id}")
    public ResponseEntity<Object> removerOrgao(@PathVariable Long id){
        if(orgaoService.apagarOrgao(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o órgão competente"));
    }

    // ------------------------ LISTAR E EXCLUIR DENÚNCIAS ------------------------------ //

    @GetMapping("/listar-denuncias")
    public ResponseEntity<Object> buscarDenuncias(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Denuncia> denunciaList=denunciaService.listarTodasDenuncias();
        return ResponseEntity.ok(denunciaList);
    }

    @DeleteMapping("/excluir-denuncia/{id}")
    public ResponseEntity<Object> excluirDenuncia(@PathVariable Long id){
        if(denunciaService.apagarDenuncia(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover a denúncia"));
    }

    @PostMapping("/registrar-feedback/{id}")
    public ResponseEntity<Object> registrarFeedbackDenuncia(@PathVariable Long id, @RequestBody Feedback feedback) {
        try {
            Feedback feedbackSalvo = denunciaService.salvarFeedback(id, feedback);

            return ResponseEntity.status(HttpStatus.CREATED).body(feedbackSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
