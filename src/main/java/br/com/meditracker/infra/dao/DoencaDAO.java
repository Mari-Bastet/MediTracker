package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.dominio.RepositorioDoenca;
import br.com.meditracker.dominio.Medicamento;

public class DoencaDAO implements RepositorioDoenca {
	
    Connection conn = new ConnectionFactory().criaConexao();

	public ArrayList<Doenca> listaDoencas() {
	    
	    ArrayList<Doenca> doencas = new ArrayList<>();
	    
		String sqlSelect = "select * from tb_mtc_doenca";
		
		try  {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Doenca doenca = new Doenca();
				
				doenca.setIdDoenca(rs.getInt("ID_DOENCA"));
				doenca.setNomeDoenca(rs.getString("NOME_DOENCA"));
				
				doencas.add(doenca);
		}
			
					
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
			
			
		}
		return doencas;
		
	}
	
	public void fecharConexao() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
