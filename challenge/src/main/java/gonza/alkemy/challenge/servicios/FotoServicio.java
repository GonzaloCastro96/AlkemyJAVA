package gonza.alkemy.challenge.servicios;

import gonza.alkemy.challenge.entidades.Foto;
import gonza.alkemy.challenge.errores.ErrorServicio;
import gonza.alkemy.challenge.repositorios.FotoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    FotoRepositorio fr;
    
    @Transactional
    public Foto guardar(MultipartFile archivo) throws ErrorServicio {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fr.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo)throws ErrorServicio{
          if (archivo != null) {
            try {
                Foto foto = new Foto();
                if(idFoto!=null){
                    Optional<Foto>respuesta=fr.findById(idFoto);
                    if(respuesta.isPresent()){
                        foto = respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fr.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
