package unoeste.fipp.ativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante.entities.Orgao;
import unoeste.fipp.ativooperante.repositories.OrgaoRepository;

import java.util.List;

@Service
public class OrgaoService {
    @Autowired
    OrgaoRepository orgaoRepository;

    public List<Orgao> listarTodosOrgaos(){
        List<Orgao> orgaoList = orgaoRepository.findAll();
        return orgaoList;
    }

    public Orgao buscarPorId(Long id){
        return orgaoRepository.findById(id).orElse(null);
    }

    public List<Orgao> buscarOrgaoPorKW(String keyword){
        List <Orgao> orgaoList=orgaoRepository.findByKW(keyword.toUpperCase());
        return orgaoList;
    }

    public Orgao inserirOrgao(Orgao orgao){
        orgao = orgaoRepository.save(orgao);
        return orgao;
    }

    public boolean apagarOrgao(Long id){
        if(buscarPorId(id) != null){
            orgaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
