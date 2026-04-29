package unoeste.fipp.projetoativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.projetoativooperante.entities.Usuario;
import unoeste.fipp.projetoativooperante.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodosUsuarios(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList;
    }

    public boolean verificarEmail(String email){
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email);
        return usuarioEncontrado == null;
    }

    public Usuario salvarUsuario(Usuario novoUsuario){
        if(verificarEmail(novoUsuario.getEmail())){
            novoUsuario.setNivel("cidadão");
            novoUsuario = usuarioRepository.save(novoUsuario);
            return novoUsuario;
        }
        return null;
    }
}
