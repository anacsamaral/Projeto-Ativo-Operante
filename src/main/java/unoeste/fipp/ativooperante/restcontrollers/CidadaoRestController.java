package unoeste.fipp.ativooperante.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unoeste.fipp.ativooperante.entities.*;
import unoeste.fipp.ativooperante.security.JWTTokenProvider;
import unoeste.fipp.ativooperante.services.DenunciaService;
import unoeste.fipp.ativooperante.services.OrgaoService;
import unoeste.fipp.ativooperante.services.TipoService;

import java.util.List;

@RestController
@RequestMapping("apis/cidadao")
public class CidadaoRestController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    DenunciaService denunciaService;
    @Autowired
    OrgaoService orgaoService;
    @Autowired
    TipoService tipoService;

    @GetMapping("/listar-denuncias/{id}")
    public ResponseEntity<Object> buscarDenuncias(@PathVariable Long id){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Denuncia> denunciaList=denunciaService.listarDenunciasPorCidadao(id);
        return ResponseEntity.ok(denunciaList);
    }

    @GetMapping("/listar-orgaos")
    public ResponseEntity<Object> buscarOrgaos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Orgao> orgaoList=orgaoService.listarTodosOrgaos();
        return ResponseEntity.ok(orgaoList);
    }

    @GetMapping("/listar-tipos")
    public ResponseEntity<Object> buscarTipos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    @GetMapping("/listar-tipos/{nome}")
    public ResponseEntity<Object> buscarTipoPorNome(@PathVariable String nome){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    @GetMapping("/listar-feedbacks")
    public ResponseEntity<Object> buscarFeedbacks(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Feedback> feedbackList=denunciaService.listarTodosFeedbacks();
        return ResponseEntity.ok(feedbackList);
    }

    @PostMapping("/registrar-denuncia")
    public ResponseEntity<Object> registrarDenuncia(@RequestBody Denuncia denuncia){
        denuncia=denunciaService.inserirDenuncia(denuncia);
        if(denuncia!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao registrar denúncia"));
    }
}
