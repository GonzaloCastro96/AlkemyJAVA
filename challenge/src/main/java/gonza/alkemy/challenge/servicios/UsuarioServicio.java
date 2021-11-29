package gonza.alkemy.challenge.servicios;

import gonza.alkemy.challenge.entidades.Usuario;
import gonza.alkemy.challenge.errores.ErrorServicio;
import gonza.alkemy.challenge.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio ur;
    
    @Autowired
    private NotificacionServicio nf;

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String mail, String clave) throws ErrorServicio {
        validar(nombre, apellido, mail, clave);

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setMail(mail);
        String encriptada= new BCryptPasswordEncoder().encode(clave);
        u.setClave(encriptada);
        u.setAlta(new Date());
        
        ur.save(u);
        
        nf.enviar("Bienvenido a Mascotapp", "Registro Mascotapp", u.getMail());
    }
    
    @Transactional
    public void modificar(MultipartFile archivo, String id, String nombre, String apellido, String mail, String clave) throws ErrorServicio {
        validar(nombre, apellido, mail, clave);
        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Usuario u = ur.findById(id).get();
            u.setApellido(apellido);
            u.setNombre(nombre);
            u.setMail(mail);
            String encriptada= new BCryptPasswordEncoder().encode(clave);
            u.setClave(encriptada);
            
            ur.save(u);
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");
        }
    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Usuario u = ur.findById(id).get();
            u.setBaja(new Date());
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");
        }
        
    }
    
    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Usuario u = ur.findById(id).get();
            u.setBaja(null);
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");
        }
        
    }

    private void validar(String nombre, String apellido, String mail, String clave) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede ser nulo");
        }
        if(mail.contains("@")==false){
            throw new ErrorServicio("El mail debe contener @");
        }
        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave del usuario no puede ser nula y tiene que tener mas de 6 caracteres");
        }
    }

}
