package unoeste.fipp.projetoativooperante.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.entities.Feedback;
import unoeste.fipp.projetoativooperante.security.JWTTokenProvider;
import unoeste.fipp.projetoativooperante.services.DenunciaService;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("apis/cidadao")
public class CidadaoRestController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    DenunciaService denunciaService;

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
        List<Autor> autorList=autorService.buscarTodosAutores();
        return ResponseEntity.ok(autorList);
    }

    // LISTAR TIPOS DE PROBLEMAS]
    @GetMapping("/listar-tipos-problemas")
    public ResponseEntity<Object> buscarOrgaos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Autor> autorList=autorService.buscarTodosAutores();
        return ResponseEntity.ok(autorList);
    }

    // VISUALIZAR FEEDBACKS
    @GetMapping("/listar-feedbacks")
    public ResponseEntity<Object> buscarOrgaos(){
        String token=request.getHeader("Authorization");
        if (!JWTTokenProvider.verifyToken(token))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Feedback> feedbackList=denunciaService.buscarTodosFeedbacks();
        return ResponseEntity.ok(autorList);
    }
}
