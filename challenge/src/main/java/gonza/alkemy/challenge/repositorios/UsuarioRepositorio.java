package gonza.alkemy.challenge.repositorios;

import gonza.alkemy.challenge.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    @Query("SELECT c FROM Usuario c WHERE c.id= :id")
    public Usuario buscarporId(@Param("id") String id);
}
