package br.aeso.ex01Maur�cio.exerc�cio.util;

import br.aeso.ex01Maur�cio.exerc�cio.exceptions.projetoException;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Endere�o;
import br.aeso.ex01Maur�cio.exerc�cio.modelo.Fornecedor;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.IRepositorio;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioEnderecoJDBC;
import br.aeso.ex01Maur�cio.exerc�cio.repositorio.RepositorioFornecedorJDBC;

public class testeJDBC {

	public static void main(String[] args) {

		IRepositorio<Fornecedor, String> repositorio = new RepositorioFornecedorJDBC();
		IRepositorio<Endere�o, Integer> repositorioEnderecos = new RepositorioEnderecoJDBC();

		Fornecedor fornecedor = new Fornecedor(null,null,null);
		fornecedor.setNome("Matheus Faustino");
		fornecedor.setBanco("Caixa");
		fornecedor.setCpf("08460611477");
		fornecedor.setID(3);
		
		Fornecedor fornecedor2 = new Fornecedor(null,null,null);
		fornecedor2.setNome("Iago");
		fornecedor2.setBanco("Ita�");
		fornecedor2.setCpf("08460611478");
		
		Fornecedor fornecedor3 = new Fornecedor(null,null,null);
		fornecedor3.setNome("hugo");
		fornecedor3.setBanco("Ita�");
		fornecedor3.setCpf("08460611479");
		
		Endere�o endereco = new Endere�o(null,null,null,null,null,null,null);
		endereco.setBairro("Peixinhos");
		endereco.setCep("53220350");
		endereco.setCidade("Olinda");
		endereco.setComplemento("casa");
		endereco.setNumero("56");
		endereco.setRua("Progresso");
		endereco.setFornecedor(fornecedor);
		
		Endere�o endereco2 = new Endere�o(null,null,null,null,null,null,null);
		endereco2.setBairro("Px");
		endereco2.setCep("53220350");
		endereco2.setCidade("Olinda");
		endereco2.setComplemento("casa");
		endereco2.setNumero("56");
		endereco2.setRua("Progresso");
		endereco2.setFornecedor(fornecedor);

		try {
			//repositorio.cadastrar(fornecedor); 
			//repositorio.cadastrar(fornecedor2); System.out.println("cadastrado!");
			//repositorio.cadastrar(fornecedor3); System.out.println("cadastrado!");
			//System.out.println(repositorio.remover("08460611477"));
			//System.out.println(repositorio.procurar("08460611477"));
			//repositorio.atualizar(fornecedor);
			System.out.println(repositorio.listarEntities());
			//repositorioEnderecos.cadastrar(endereco2); System.out.println("cadastrado!");
			//System.out.println(repositorioEnderecos.listarEntities());
			
		} catch (projetoException e) {
			System.out.println(e.getMessage());
		}

	}

}
