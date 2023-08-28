package com.in28minutes.learnspringboot; // nome do pacote onde esta a classe java (diretorio)
// importa classes do springboot, lembrando que necessita estar no pom.xml para funcionar
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringFrameworkApplication {
//Classe java padrao do SpringBoot que inicializa a aplicacao

//qualquer codigo java precisa ter o metod	main para ser executado pelo java virtual machine (JVM)

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringFrameworkApplication.class, args);
	}

}
