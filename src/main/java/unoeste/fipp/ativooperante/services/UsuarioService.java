package unoeste.fipp.ativooperante.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante.entities.Usuario;
import unoeste.fipp.ativooperante.repositories.UsuarioRepository;

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

    public Usuario inserirUsuario(Usuario novoUsuario){
        if(verificarEmail(novoUsuario.getEmail())){
            novoUsuario.setNivel(2);
            novoUsuario = usuarioRepository.save(novoUsuario);
            return novoUsuario;
        }
        return null;
    }

    public Usuario promoverParaAdmin(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && usuario.getNivel() != 1) {
            usuario.setNivel(1);
            return usuarioRepository.save(usuario);
        }
        return usuario;
    }
}
