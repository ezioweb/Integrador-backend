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
        Perfil perfilUser = new Perfil();
        perfilAdmin.setDescricao("ADMIN");
        perfilUser.setDescricao("USER");
        List<Perfil> listaPerfil1 = new ArrayList<>();
        List<Perfil> listaPerfil2 = new ArrayList<>();
        listaPerfil1.add(perfilAdmin);
        listaPerfil2.add(perfilUser);

        Usuario usuario1 = new Usuario();
        usuario1.setPassword(encoder.encode("123456"));
        usuario1.setUsername("admin1");
        usuario1.setPerfis(listaPerfil1);

        Usuario usuario2 = new Usuario();
        usuario2.setPassword(encoder.encode("654321"));
        usuario2.setUsername("user2");
        usuario2.setPerfis(listaPerfil2);

        repository.save(usuario1);
        repository.save(usuario2);
    }
}
