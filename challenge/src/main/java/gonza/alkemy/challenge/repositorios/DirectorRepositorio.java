package gonza.alkemy.challenge.repositorios;


import gonza.alkemy.challenge.entidades.Director;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepositorio extends JpaRepository<Director, String> {
    
    @Query("SELECT c FROM Director c WHERE c.Director.Nombre= :nombre")
    public List<Director> BuscarDirectorPorNombre(@Param ("nombre") String nombre);
}
