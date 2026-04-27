package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.projetoativooperante.entities.Orgao;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao,Long> {

}
