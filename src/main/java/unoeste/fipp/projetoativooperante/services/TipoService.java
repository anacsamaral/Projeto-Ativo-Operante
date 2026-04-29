package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Tipo;
import unoeste.fipp.projetoativooperante.repositories.TipoRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    TipoRepository tipoRepository;
    public List<Tipo> tipoList = tipoRepository.findAll();
}
