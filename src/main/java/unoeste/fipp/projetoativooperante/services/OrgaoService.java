package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.entities.Orgao;
import unoeste.fipp.projetoativooperante.repositories.OrgaoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrgaoService {
    @Autowired
    OrgaoRepository orgaoRepository;

    public List<Orgao> listarTodosOrgaos(){
        List<Orgao> orgaoList = orgaoRepository.findAll();
        return orgaoList;
    }

    public List<Denuncia> listarDenunciasPorCidadao(Long id){
        List<Denuncia> denunciaList = orgaoRepository.findAll();
        return denunciaList;
    }

    public Denuncia buscarPorId(Long id){
        return orgaoRepository.findById(id).orElse(null);
    }

    public Denuncia inserirOrgao(Orgao orgao){
        orgao = orgaoRepository.save(orgao);
        return orgao;
    }

    public boolean apagarDenuncia(Long id){
        if(buscarPorId(id) != null){
            orgaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
