package com.example.demo;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class ExemploJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploJpaApplication.class, args);
	}
	
//	@Primary
//	@Bean
	public CommandLineRunner inserindoAlunos(RepositorioAluno repository) {
		return (args) -> {
			Aluno a = new Aluno();
			a.nome = "Melo";
			a.cpf = "123";
			a = repository.save(a);
			System.out.println("Aluno: " + a.ID + " Nome: " + a.nome+ " salvo com sucesso");
			Aluno a2 = new Aluno();
			a2.nome = "Daniel";
			a2.cpf = "345";
			a2 = repository.save(a2);
			System.out.println("Aluno: " + a2.ID + " Nome: " + a2.nome+ " salvo com sucesso");
			Aluno a3 = new Aluno();
			a3.nome = "Camila";
			a3.cpf = "456";
			a3 = repository.save(a3);
			System.out.println("Aluno: " + a3.ID + " Nome: " + a3.nome+ " salvo com sucesso");
		};
	}
	@Bean
	public CommandLineRunner listandoAlunos(RepositorioAluno repository) {
		return (args) -> {
			for (Aluno aluno: repository.findAll()) {
				System.out.println(aluno.nome);
			}
		};
	}
	
//	@Bean
	public CommandLineRunner atualizandoUmAluno(RepositorioAluno repository) {
		return (args) -> {
			Optional<Aluno> alunoPesquisado = repository.findById(1l);
			if (alunoPesquisado.isPresent()) {
				Aluno alunoAlterar = alunoPesquisado.get();
				alunoAlterar.nome = "Novo nome";
				repository.save(alunoAlterar);
				System.out.println("Aluno: " + alunoAlterar.ID + " Nome: " + alunoAlterar.nome+ "salvo com sucesso");
				System.out.println("Listando os aluno");
				for (Aluno aluno: repository.findAll()) {
					System.out.println(aluno.nome);
				}

			}
		};
	}
	
//	@Bean
	public CommandLineRunner removendoUmAluno(RepositorioAluno repository) {
		return (args) -> {
			Optional<Aluno> alunoPesquisado = repository.findById(1l);
			if (alunoPesquisado.isPresent()) {
				Aluno alunoRemover = alunoPesquisado.get();
				repository.deleteById(alunoRemover.ID);
				System.out.println("Aluno: " + alunoRemover.ID + " Nome: " + alunoRemover.nome+ "removido com sucesso");
				System.out.println("Listando os aluno");
				for (Aluno aluno: repository.findAll()) {
					System.out.println(aluno.nome);
				}

			}
		};
	}
	

}
