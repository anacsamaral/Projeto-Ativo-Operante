package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.projetoativooperante.entities.Denuncia;
import unoeste.fipp.projetoativooperante.entities.Orgao;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia,Long> {
    public List<Denuncia> findByUrgencia(int urgencia);

    public List<Denuncia> findByOrgao(Orgao orgao);

    public List<Denuncia> findByUrgenciaAndOrgao(int Urgencia, Orgao orgao);
}
