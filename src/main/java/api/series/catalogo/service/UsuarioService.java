package api.series.catalogo.service;

import api.series.catalogo.model.Role;
import api.series.catalogo.model.Usuario;
import api.series.catalogo.repository.RoleRepository;
import api.series.catalogo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void save(String username, String password, Set<String> roles){
        //Criar o Set de Roles, pesquisar as roles pelo nome e adicionar no usuário
        Set<Role> roleSet = new HashSet<>();
        for (String nome : roles){
            Role role = roleRepository.findByName(nome);
            if (role != null) roleSet.add(role);
        }
        //Criar um usuário com os dados para serem salvos
        Usuario usuario = new Usuario(username, password, roleSet);
        //Salva o usuário no banco de dados
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Pesquisa o usuário pelo username
        Usuario usuario = usuarioRepository.findByUsername(username);
        //Valida se o usuário existe
        if (usuario == null)
            throw new UsernameNotFoundException("Usuário não encontrado");

        //Transformar as Roles do usuário em SimpleGrantedAuthority
        Set<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toSet());

        //retornar um User
        return new User(username, usuario.getPassword(), authorities);
    }
}
