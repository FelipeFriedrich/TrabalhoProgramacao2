package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Produto;

/**
 *
 * @author assparremberger
 */
public class ProdutoDAO {
    
    public static void inserir(Produto produto){
       String query = "INSERT INTO produtos "
           + "( nome, preco, quantidade, codCategoria)"
           + " VALUES ( "
           + " '" + produto.getNome()           + "' , "
           + " " + produto.getPreco()          + " , "
           + "  " + produto.getQuantidade()     + " , "
           + " " + produto.getCategoria().getId()+ ") ";
        JOptionPane.showMessageDialog(null, query);
        Conexao.executar(query);
        
    }
    public static void editar(Produto produto){
        String query = "UPDATE produtos SET "
           + " nome =         '" + produto.getNome()           + "' , "
           + " preco =         " + produto.getPreco()          + " , "
           + " quantidade =    " + produto.getQuantidade()    + "  , "
           + " codCategoria =  " + produto.getCategoria().getId() + "    "
           + " WHERE id = " + produto.getId();
        Conexao.executar(query);
        
    }
    
    public static void excluir(int produto){
        String query = "DELETE FROM produtos "
                     + " WHERE id = " + produto;
        Conexao.executar(query);
    }
    
 /*   public static List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT c.id, c.nome, c.email, "
                + " c.cpf_cnpj, c.tipo, c.receberEmail, "
                + " d.id, d.nome "
                + " FROM clientes c "
                + " INNER JOIN cidades d "
                + " ON c.codCidade = d.id ";
   
        ResultSet rs = Conexao.consultar( query );
        
        if( rs != null ){
            
            try {
                
                while( rs.next() ){
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 7 ) );
                    cid.setNome( rs.getString( 8 ) );
                    Cliente cliente = new Cliente();
                    cliente.setCidade( cid );
                    cliente.setId( rs.getInt( 1 ) );
                    cliente.setNome( rs.getString( 2 ) );
                    cliente.setEmail( rs.getString( 3 ) );
                   
                    if( rs.getInt( 6 ) == 1 )
                        cliente.setReceberEmail( true );
                    else
                        cliente.setReceberEmail( false );
                  
                    if( rs.getString( 5 ).equals(Cliente.PESSOA_FISICA)){
                        // Aqui possui um erro que será corrigido
                        ClientePF pf = (ClientePF) cliente;
                        pf.setTipo( Cliente.PESSOA_FISICA );
                        pf.setCpf( rs.getString( 4 ) );
                        lista.add( pf );
                    }else{
                        // Aqui possui um erro que será corrigido
                        ClientePJ pj = (ClientePJ) cliente;
                        pj.setTipo( Cliente.PESSOA_JURIDICA );
                        pj.setCnpj(rs.getString( 4 ) );
                        lista.add( pj );
                    }
                }
                
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            
        }
        
        
        return lista;
    }
   */ 
    
    
 
    
    public static List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<>();
        String query = 
            "SELECT p.id, p.nome, p.preco, p.quantidade, "
                + " c.id, c.nome"
                + " FROM produtos p "
                + " INNER JOIN categorias c "
                + " ON p.codCategoria = c.id ";
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 5 ) );
                    cat.setNome( rs.getString( 6 ) );
                    Produto produto = new Produto();
                    produto.setId( rs.getInt( 1 ) );
                    produto.setNome( rs.getString( 2 ) );
                    produto.setPreco(rs.getDouble( 3 ));
                    produto.setQuantidade( rs.getDouble( 4 ) );
                    produto.setCategoria( cat );
                    lista.add( produto );
                    
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
}
