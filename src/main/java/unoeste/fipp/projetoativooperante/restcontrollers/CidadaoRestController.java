package unoeste.fipp.projetoativooperante.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.projetoativooperante.entities.*;
import unoeste.fipp.projetoativooperante.security.JWTTokenProvider;
import unoeste.fipp.projetoativooperante.services.DenunciaService;
import unoeste.fipp.projetoativooperante.services.OrgaoService;
import unoeste.fipp.projetoativooperante.services.TipoService;

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

    // LISTAR DENÚNICAS DE UM CIDADÃO ESPECÍFICO
    @GetMapping("/listar-denuncias/{id}")
    public ResponseEntity<Object> buscarDenuncias(@PathVariable Long id){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Denuncia> denunciaList=denunciaService.listarDenunciasCidadao(id);
        return ResponseEntity.ok(denunciaList);
    }

    // LISTAR ÓRGÃOS COMPETENTES
    @GetMapping("/listar-orgaos")
    public ResponseEntity<Object> buscarOrgaos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Orgao> orgaoList=orgaoService.listarTodosOrgaos();
        return ResponseEntity.ok(orgaoList);
    }

    // LISTAR TIPOS DE PROBLEMAS
    @GetMapping("/listar-tipos")
    public ResponseEntity<Object> buscarTipos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    // VISUALIZAR FEEDBACKS
    @GetMapping("/listar-feedbacks")
    public ResponseEntity<Object> buscarFeedbacks(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Feedback> feedbackList=denunciaService.listarTodosFeedbacks();
        return ResponseEntity.ok(feedbackList);
    }
}
