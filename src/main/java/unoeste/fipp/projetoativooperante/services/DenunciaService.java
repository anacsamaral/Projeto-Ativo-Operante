package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.entities.Feedback;
import unoeste.fipp.projetoativooperante.repositories.DenunciaRepository;
import unoeste.fipp.projetoativooperante.repositories.FeedbackRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    DenunciaRepository denunciaRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    // save, delete, findOne, findAll - PARA TODOS OS SERVICES

    public List<Denuncia> listarTodasDenuncias(){
        List<Denuncia> denunciaList = denunciaRepository.findAll();
        return denunciaList;
    }

    public List<Denuncia> listarDenunciasCidadao(Long id){
        List<Denuncia> denunciaList = denunciaRepository.findAll();
        return denunciaList;
    }

    public Denuncia buscarPorId(Long id){
        return denunciaRepository.findById(id).orElse(null);
    }

    public Denuncia inserirDenuncia(Denuncia novaDenuncia){
        novaDenuncia.setData(LocalDateTime.now());
        novaDenuncia = denunciaRepository.save(novaDenuncia);
        return novaDenuncia;
    }

    public boolean apagarDenuncia(Long id){
        if(buscarPorId(id) != null){
            denunciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Feedback> listarTodosFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList;
    }
}
