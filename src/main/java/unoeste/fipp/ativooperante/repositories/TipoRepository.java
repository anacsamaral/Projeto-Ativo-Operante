package unoeste.fipp.ativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.ativooperante.entities.Tipo;

import java.util.List;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Long> {
}
