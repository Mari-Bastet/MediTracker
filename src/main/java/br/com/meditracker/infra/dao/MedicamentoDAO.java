package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.meditracker.dominio.Medicamento;

public class MedicamentoDAO {
	
    Connection conn = new ConnectionFactory().criaConexao();

	public ArrayList<Medicamento> listaMedicamentos() {
	    
	    ArrayList<Medicamento> medicamentos = new ArrayList<>();
	    
		String sqlSelect = "select medi.id_med_dosagem"
				+ ",               medi.nome_medicamento "
				+ ",               medi.descricao_medicamento "
				+ ",               medo.dosagem_medicamento "
				+ "from tb_mtc_medicamento   medi "
				+ ",    tb_mtc_forma_dosagem ford "
				+ ",    tb_mtc_med_dosagem   medo "
				+ "where medi.id_medicamento  = medo.id_medicamento "
				+ "and   ford.id_tipo_dosagem = medo.id_tipo_dosagem "
				+ "order by nome_medicamento";
		
		try  {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Medicamento medicamento = new Medicamento();
				
				medicamento.setIdMedicamento(rs.getInt("ID_MED_DOSAGEM"));
				medicamento.setDescricaoMedicamento(rs.getString("DESCRICAO_MEDICAMENTO"));
				medicamento.setNomeMedicamento(rs.getString("NOME_MEDICAMENTO"));
				medicamento.setDosagemMedicamento(rs.getDouble("DOSAGEM_MEDICAMENTO"));
				
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
