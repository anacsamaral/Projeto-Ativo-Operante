package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.projetoativooperante.entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
