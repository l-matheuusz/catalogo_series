package api.series.catalogo.configuration;

import api.series.catalogo.model.Role;
import api.series.catalogo.model.Usuario;
import api.series.catalogo.repository.UsuarioRepository;
import api.series.catalogo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setLabel("Admin");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            userRole.setLabel("User");
            roleRepository.save(userRole);

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);

            Usuario user = new Usuario();
            user.setUsername("customer");
            user.setPassword(passwordEncoder.encode("customer"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        };
    }
}
