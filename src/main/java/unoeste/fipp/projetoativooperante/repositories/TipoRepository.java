package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.projetoativooperante.entities.Tipo;

import java.util.List;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Long> {

    @Query(value = "SELECT * FROM autor WHERE upper(aut_nome) LIKE %:keyword%",nativeQuery = true)
    public List<Tipo> findById(@Param("id") String id);
}
