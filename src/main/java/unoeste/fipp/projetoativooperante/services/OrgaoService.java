package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Orgao;
import unoeste.fipp.projetoativooperante.repositories.OrgaoRepository;

import java.util.List;

@Service
public class OrgaoService {
    @Autowired
    OrgaoRepository orgaoRepository;
    public List<Orgao> orgaoList = orgaoRepository.findAll();
}
