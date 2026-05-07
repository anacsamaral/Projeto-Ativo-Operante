package unoeste.fipp.ativooperante.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
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

    // verificar token e nivel
    private ResponseEntity<Object> validarAcessoAdmin() {
        String token = request.getHeader("Authorization");

        if (token == null || !JWTTokenProvider.verifyToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        io.jsonwebtoken.Claims detalhes = JWTTokenProvider.getAllClaimsFromToken(token);
        if (detalhes != null && detalhes.get("nivel") != null) {
            String nivel = detalhes.get("nivel").toString();
            if (nivel.equals("2")) { // 2 = Cidadão
                return new ResponseEntity<>("Acesso restrito à prefeitura", HttpStatus.FORBIDDEN);
            }
        }
        return null; // Tudo OK!
    }

    // ------------------------ TIPOS DE PROBLEMA ------------------------------ //

    @PostMapping("/adicionar-tipo")
    public ResponseEntity<Object> adicionarTipo(@RequestBody Tipo tipo){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        tipo = tipoService.inserirTipo(tipo);
        if(tipo != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o tipo de problema"));
    }

    @GetMapping("/listar-tipos")
    public ResponseEntity<Object> buscarTipos(){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Tipo> tipoList=tipoService.listarTodosTipos();
        return ResponseEntity.ok(tipoList);
    }

    @GetMapping("/listar-tipos/{keyword}")
    public ResponseEntity<Object> buscarTiposPorKW(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Tipo> tipoList=tipoService.buscarTipoPorKW(keyword);
        return ResponseEntity.ok(tipoList);
    }

    @PutMapping("/alterar-tipo")
    public ResponseEntity<Object> atualizarTipo(@RequestBody Tipo tipo){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        tipo=tipoService.inserirTipo(tipo);
        if(tipo!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o tipo de problema"));
    }

    @DeleteMapping("/excluir-tipo/{id}")
    public ResponseEntity<Object> removerTipo(@PathVariable Long id){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        if(tipoService.apagarTipo(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o tipo de problema"));
    }

    // ------------------------ ORGAO COMPETENTE ------------------------------ //

    @PostMapping("/adicionar-orgao")
    public ResponseEntity<Object> adicionarOrgao(@RequestBody Orgao orgao){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        orgao = orgaoService.inserirOrgao(orgao);
        if(orgao != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao inserir o órgão competente"));
    }

    @GetMapping("/listar-orgaos")
    public ResponseEntity<Object> buscarOrgaos(){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Orgao> orgaoList=orgaoService.listarTodosOrgaos();
        return ResponseEntity.ok(orgaoList);
    }

    @GetMapping("/listar-orgaos/{keyword}")
    public ResponseEntity<Object> buscarOrgaosPorKW(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Orgao> orgaoList=orgaoService.buscarOrgaoPorKW(keyword);
        return ResponseEntity.ok(orgaoList);
    }

    @PutMapping("/alterar-orgao")
    public ResponseEntity<Object> atualizarOrgao(@RequestBody Orgao orgao){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        orgao=orgaoService.inserirOrgao(orgao);
        if(orgao!=null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o órgão competente"));
    }

    @DeleteMapping("/excluir-orgao/{id}")
    public ResponseEntity<Object> removerOrgao(@PathVariable Long id){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        if(orgaoService.apagarOrgao(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover o órgão competente"));
    }

    // ------------------------ DENÚNCIA ------------------------------ //

    @GetMapping("/listar-denuncias")
    public ResponseEntity<Object> buscarDenuncias(){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Denuncia> denunciaList=denunciaService.listarTodasDenuncias();
        return ResponseEntity.ok(denunciaList);
    }

    @GetMapping("/listar-denuncias/{keyword}")
    public ResponseEntity<Object> buscarDenunciasPorKW(@PathVariable String keyword){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        List<Denuncia> denunciaList=denunciaService.buscarDenunciaPorKW(keyword);
        return ResponseEntity.ok(denunciaList);
    }

    @DeleteMapping("/excluir-denuncia/{id}")
    public ResponseEntity<Object> excluirDenuncia(@PathVariable Long id){
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        if(denunciaService.apagarDenuncia(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao remover a denúncia"));
    }

    // ------------------------ FEEDBACK ------------------------------ //

    @PostMapping("/registrar-feedback/{id}")
    public ResponseEntity<Object> registrarFeedbackDenuncia(@PathVariable Long id, @RequestBody Feedback feedback) {
        ResponseEntity<Object> erroAcesso = validarAcessoAdmin();
        if (erroAcesso != null) return erroAcesso;

        try {
            Feedback feedbackSalvo = denunciaService.salvarFeedback(id, feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body(feedbackSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}