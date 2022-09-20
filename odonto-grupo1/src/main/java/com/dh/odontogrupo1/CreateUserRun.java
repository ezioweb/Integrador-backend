package com.dh.odontogrupo1;

import com.dh.odontogrupo1.model.Perfil;
import com.dh.odontogrupo1.model.Usuario;
import com.dh.odontogrupo1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Perfil perfilAdmin = new Perfil();
        perfilAdmin.setDescricao("ROLE_ADMIN");
        List<Perfil> listaPerfil1 = new ArrayList<>();
        listaPerfil1.add(perfilAdmin);

        Usuario usuario1 = new Usuario();
        usuario1.setPassword(encoder.encode("123456"));
        usuario1.setUsername("user1");
        usuario1.setPerfis(listaPerfil1);

        repository.save(usuario1);

    }
}
