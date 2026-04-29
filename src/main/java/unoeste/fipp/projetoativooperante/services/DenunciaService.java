package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.repositories.DenunciaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    DenunciaRepository denunciaRepository;

    public List<Denuncia> buscarTodasDenuncias(){
        List<Denuncia> denunciaList = denunciaRepository.findAll();
        return denunciaList;
    }

    public Denuncia buscarPorId(Long id){
        return denunciaRepository.findById(id).orElse(null);
    }

    public Denuncia salvarDenuncia(Denuncia novaDenuncia){
        novaDenuncia.setData(LocalDateTime.now());
        novaDenuncia = denunciaRepository.save(novaDenuncia);
        return novaDenuncia;
    }
}
