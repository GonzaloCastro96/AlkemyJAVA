package gonza.alkemy.challenge.repositorios;

import gonza.alkemy.challenge.entidades.Actor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepositorio extends JpaRepository<Actor, String>{
    
    @Query("SELECT c FROM Actor c WHERE c.Actor.Nombre= :nombre")
    public List<Actor> BuscarActorPorNombre(@Param ("nombre") String nombre);
}
