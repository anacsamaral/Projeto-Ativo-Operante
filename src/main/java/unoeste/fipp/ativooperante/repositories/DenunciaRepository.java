package unoeste.fipp.ativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperante.entities.Denuncia;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia,Long> {
    public List<Denuncia> findByUsuarioId(Long id);

    @Query(value = "SELECT * FROM denuncia WHERE upper(den_titulo) LIKE %:keyword%",nativeQuery = true)
    public List<Denuncia> findByKW(@Param("keyword") String keyword);
}
