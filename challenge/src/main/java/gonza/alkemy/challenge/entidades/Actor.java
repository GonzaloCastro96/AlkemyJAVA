package gonza.alkemy.challenge.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

public class Actor {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    @OneToMany
    private Pelicula pelicula;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    
}
