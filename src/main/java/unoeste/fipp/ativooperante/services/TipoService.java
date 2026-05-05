package unoeste.fipp.ativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante.entities.Tipo;
import unoeste.fipp.ativooperante.repositories.TipoRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    TipoRepository tipoRepository;

    // LISTAR
    public List<Tipo> listarTodosTipos(){
        List<Tipo> tipoList = tipoRepository.findAll();
        return tipoList;
    }

    public Tipo buscarTipo(Long id){
        return tipoRepository.findById(id).orElse(null);
    }

    public Tipo inserirTipo(Tipo tipo){
        try {
            tipo = tipoRepository.save(tipo);
            return tipo;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean apagarTipo(Long id){
        if(buscarTipo(id) != null){
            tipoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
