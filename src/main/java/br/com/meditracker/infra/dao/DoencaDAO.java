package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.meditracker.dominio.Medicamento;

public class DoencaDAO {
	
    Connection conn = new ConnectionFactory().criaConexao();

	public ArrayList<Medicamento> listaMedicamentos() {
	    
	    ArrayList<Medicamento> medicamentos = new ArrayList<>();
	    
		String sqlSelect = "select * from tb_mtc_medicamento";
		
		try  {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Medicamento medicamento = new Medicamento();
				
				medicamento.setId_medicamento(rs.getInt("ID_MEDICAMENTO"));
				medicamento.setDescricao_medicamento(rs.getString("DESCRICAO_MEDICAMENTO"));
				medicamento.setNome_medicamento(rs.getString("NOME_MEDICAMENTO"));
				medicamento.setDosagem_medicamento(rs.getDouble("DOSAGEM_MEDICAMENTO"));
				
				medicamentos.add(medicamento);
		}
			
					
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
			
			
		}
		return medicamentos;
		
	}
	
	public void fecharConexao() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
