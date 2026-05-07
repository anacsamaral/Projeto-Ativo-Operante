package unoeste.fipp.ativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperante.entities.Denuncia;
import unoeste.fipp.ativooperante.entities.Orgao;

import java.util.List;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao,Long> {

    @Query(value = "SELECT * FROM orgao WHERE upper(org_nome) LIKE %:keyword%",nativeQuery = true)
    public List<Orgao> findByKW(@Param("keyword") String keyword);
}
