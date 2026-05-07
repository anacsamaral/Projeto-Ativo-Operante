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

    // verifica token e nivel
    private ResponseEntity<Object> validarAcessoCidadao() {
        String token = request.getHeader("Authorization");

        if (token == null || !JWTTokenProvider.verifyToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        io.jsonwebtoken.Claims detalhes = JWTTokenProvider.getAllClaimsFromToken(token);
        if (detalhes != null && detalhes.get("nivel") != null) {
            String nivel = detalhes.get("nivel").toString();
            if (nivel.equals("1")) { // 1 = Administrador (Impede a prefeitura de usar rotas do cidadão)
                return new ResponseEntity<>("Acesso restrito ao aplicativo do cidadão", HttpStatus.FORBIDDEN);
            }
        }
        return null;
    }

    // ------------------------ DENÚNCIAS ------------------------------ //

    @GetMapping("/listar-denuncias/id/{id}")
    public ResponseEntity<Object> buscarDenunciasPorCidadao(@PathVariable Long id){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Denuncia> denunciaList=denunciaService.listarDenunciasPorCidadao(id);
        return ResponseEntity.ok(denunciaList);
    }

    @GetMapping("/listar-denuncias/kw/{keyword}")
    public ResponseEntity<Object> buscarDenunciasPorKW(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Denuncia> denunciaList=denunciaService.buscarDenunciaPorKW(keyword);
        return ResponseEntity.ok(denunciaList);
    }

    @PostMapping("/registrar-denuncia")
    public ResponseEntity<Object> registrarDenuncia(@RequestBody Denuncia denuncia){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        denuncia = denunciaService.inserirDenuncia(denuncia);
        if(denuncia != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao registrar denúncia"));
    }

    // ------------------------ ÓRGÃOS ------------------------------ //

    @GetMapping("/listar-orgaos")
    public ResponseEntity<Object> buscarOrgaos(){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Orgao> orgaoList=orgaoService.listarTodosOrgaos();
        return ResponseEntity.ok(orgaoList);
    }

    @GetMapping("/listar-orgaos/kw/{keyword}")
    public ResponseEntity<Object> buscarOrgaosPorKW(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Orgao> orgaoList=orgaoService.buscarOrgaoPorKW(keyword);
        return ResponseEntity.ok(orgaoList);
    }

    // ------------------------ TIPOS DE PROBLEMA ------------------------------ //

    @GetMapping("/listar-tipos")
    public ResponseEntity<Object> buscarTipos(){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    @GetMapping("/listar-tipos/kw/{keyword}")
    public ResponseEntity<Object> buscarTipoPorNome(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Tipo> tipoList=tipoService.buscarTipoPorKW(keyword);
        return ResponseEntity.ok(tipoList);
    }

    // ------------------------ FEEDBACKS ------------------------------ //

    @GetMapping("/listar-feedbacks")
    public ResponseEntity<Object> buscarFeedbacks(){
        ResponseEntity<Object> erroAcesso = validarAcessoCidadao();
        if (erroAcesso != null) return erroAcesso;

        List<Feedback> feedbackList=denunciaService.listarTodosFeedbacks();
        return ResponseEntity.ok(feedbackList);
    }
}