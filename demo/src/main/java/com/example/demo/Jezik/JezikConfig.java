package com.example.demo.Jezik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

@Configuration
public class JezikConfig {
    
@Bean
CommandLineRunner commandLineRunner(JezikRepository repository){
    return args -> {
       Jezik angleski = new Jezik("en","Hello world!");
       Jezik nemski = new Jezik("de","Hallo welt!");
       Jezik slovenski = new Jezik("sl","Pozdravljen svet!");
       Jezik spanski = new Jezik("es","Hola Mundo!");
       Jezik francoski = new Jezik("fr","Bonjour le monde");
       Jezik italjanski = new Jezik("it","Ciao mondo");
       Jezik portugalski = new Jezik("pt", "Olá Mundo");
       Jezik ceski = new Jezik("cs","Ahoj světe");
       Jezik hrvaski = new Jezik("hr","Pozdrav svete");
        repository.saveAll(
            List.of(angleski,nemski,slovenski,spanski,francoski,italjanski,portugalski,ceski,hrvaski)
            );
};
}
}
