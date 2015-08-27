package br.aeso.ex01Maur�cio.exerc�cio.util;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioFornecedorJDBC;

public class testeJDBC {

	public static void main(String[] args) {

		RepositorioFornecedorJDBC repositorio = new RepositorioFornecedorJDBC();

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Matheus Santana");
		fornecedor.setBanco("Ita�");
		fornecedor.setCpf("08460611477");
		
		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setNome("Iago");
		fornecedor2.setBanco("Ita�");
		fornecedor2.setCpf("08460611478");
		
		Fornecedor fornecedor3 = new Fornecedor();
		fornecedor3.setNome("hugo");
		fornecedor3.setBanco("Ita�");
		fornecedor3.setCpf("08460611479");

		try {
			//repositorio.cadastrar(fornecedor); System.out.println("cadastrado!");
			//repositorio.cadastrar(fornecedor2); System.out.println("cadastrado!");
			//repositorio.cadastrar(fornecedor3); System.out.println("cadastrado!");
			//System.out.println(repositorio.remover("08460611477"));
			//System.out.println(repositorio.procurar("08460611477"));
			//repositorio.atualizar(fornecedor);
			System.out.println(repositorio.listarEntities());
			
		} catch (projetoException e) {
			System.out.println(e.getMessage());
		}

	}

}
