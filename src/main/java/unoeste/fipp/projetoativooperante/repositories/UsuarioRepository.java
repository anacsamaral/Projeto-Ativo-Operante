package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.projetoativooperante.entities.Usuario;

import java.util.List;

@Repository
public interface class UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByNome(String nome);

    @Query(value = "SELECT * FROM usuario WHERE upper(usu_cpf) LIKE %:keyword%", nativeQuery = true)
    public List<Usuario> findByKW(@Param("keyword") String keyword);
}
