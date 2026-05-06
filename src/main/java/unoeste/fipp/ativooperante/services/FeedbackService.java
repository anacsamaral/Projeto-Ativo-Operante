package unoeste.fipp.ativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import unoeste.fipp.ativooperante.entities.Denuncia;
import unoeste.fipp.ativooperante.entities.Feedback;
import unoeste.fipp.ativooperante.repositories.DenunciaRepository;
import unoeste.fipp.ativooperante.repositories.FeedbackRepository;

public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Transactional
    public Feedback salvarFeedback(Long denunciaId, Feedback novoFeedback) {
                Denuncia denuncia = denunciaRepository.findById(denunciaId)
                .orElseThrow(() -> new RuntimeException("Erro: Denúncia não encontrada com o ID: " + denunciaId));

        novoFeedback.setDenuncia(denuncia);
        return feedbackRepository.save(novoFeedback);
    }
}
