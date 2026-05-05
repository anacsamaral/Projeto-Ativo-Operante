package unoeste.fipp.ativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante.entities.Denuncia;
import unoeste.fipp.ativooperante.entities.Feedback;
import unoeste.fipp.ativooperante.repositories.DenunciaRepository;
import unoeste.fipp.ativooperante.repositories.FeedbackRepository;

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

    public List<Denuncia> listarDenunciasPorCidadao(Long id){
        List<Denuncia> denunciaList = denunciaRepository.findAll();
        return denunciaList;
    }

    public Denuncia buscarDenuncia(Long id){
        return denunciaRepository.findById(id).orElse(null);
    }

    public Denuncia inserirDenuncia(Denuncia novaDenuncia){
        try {
            novaDenuncia.setData(LocalDateTime.now());
            novaDenuncia = denunciaRepository.save(novaDenuncia);
            return novaDenuncia;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean apagarDenuncia(Long id){
        if(buscarDenuncia(id) != null){
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
