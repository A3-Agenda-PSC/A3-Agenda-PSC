package br.com.agenda.aplicacao;
import java.time.LocalDate;
import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contatos;

public class Main {
	public static void main(String[] args) {
		
		ContatoDAO contatoDAO = new ContatoDAO();
		Contatos contato = new Contatos();
		contato.setNome("Morgana");
		contato.setIdade(41);
		contato.setTelefone("996027963");
		contato.setEmail("mo.morgana@gmail.com");
		contato.setDataCadastro(LocalDate.now());
		//Create comentado para não salvar novo contato no momento
		//contatoDAO.save(contato);
		
		//Atualizar/update contato
		Contatos c1 = new Contatos();
		c1.setNome("Miguel");
		c1.setIdade(27);
		c1.setTelefone("99584631");
		c1.setEmail("miguel.el@gmail.com");
		c1.setDataCadastro(LocalDate.now());
		c1.setCodigo(1);
		//Update comentado para não ser chamado
		//contatoDAO.update(c1);
		
		//Deletar o contato pelo seu número de código
		//contatoDAO.deleteByCodigo(8);
		
		
		//Visualização/read dos registros do banco de dados, todos os registros
		for (Contatos c : contatoDAO.getContatos()) {
			
			System.out.println("Contato:" +c.getNome());
			
		}
	} 
}
