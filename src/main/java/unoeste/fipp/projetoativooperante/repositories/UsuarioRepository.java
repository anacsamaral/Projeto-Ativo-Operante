package unoeste.fipp.projetoativooperante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unoeste.fipp.projetoativooperante.entities.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByNome(String nome);

    @Query(value = "SELECT * FROM usuario WHERE usu_cpf LIKE %:keyword%", nativeQuery = true)
    public List<Usuario> findByKW(@Param("keyword") String keyword);

}
