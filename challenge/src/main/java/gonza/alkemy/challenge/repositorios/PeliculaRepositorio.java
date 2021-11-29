package gonza.alkemy.challenge.repositorios;

import gonza.alkemy.challenge.entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String> {
    
    @Query("SELECT c FROM Pelicula c WHERE c.Calificacion= :calificacion ORDER BY calificacion DESC")
    public List<Pelicula> buscarporMail(@Param("calificacion") String calificacion);
    
    @Query("SELECT c FROM Pelicula c WHERE c.nombre= :nombre")
    public Pelicula buscarporNombre(@Param("nombre") String nombre);
    
}
