package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.meditracker.dominio.RegistroDiario;

public class RegistroDiarioDAO {
	
    Connection conn = new ConnectionFactory().criaConexao();

	
    public void cadastraRegistroDiario(RegistroDiario registroDiario, String documento_paciente) {
    	
		String sql = "insert into tb_registro_diario (ID_REGISTRO_DIARIO ,DESCRICAO_REGISTRO ,ID_PACIENTE ,HUMOR ,DATA_REGISTRO ) values(SEQ_ID_REGISTRO_DIARIO.nextval, ?, ?, ?, ?)";
    	
        PacienteDAO pacienteDAO = new PacienteDAO();

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	
            	
                java.util.Date dataAtual = new java.util.Date();
                java.sql.Date dataInsert = new java.sql.Date(dataAtual.getTime());
                

            	/*if (paciente.getData_nascimento_paciente() != null) {
              	data_recebida = paciente.getData_nascimento_paciente();
              	dataInsert = new java.sql.Date(data_recebida.getTime());

            	}*/
              	
                pstmt.setString(1, registroDiario.getDescricao_registro());
                pstmt.setInt(2, pacienteDAO.retornaIdPaciente(documento_paciente));
                pstmt.setInt(3, registroDiario.getHumor());
                pstmt.setDate(4, dataInsert);
                pstmt.execute();
                pstmt.close();
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        		 	
    }
    
    public void alteraRegistroDiario(RegistroDiario registroDiario, String documento_paciente) {
    	
    	String sql = "update of_registro_diario set descricao_registro = ?, humor = ? where data_registro = ? and id_paciente = ?";
    	
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
            PacienteDAO pacienteDAO = new PacienteDAO();

            java.util.Date dataAtual = new java.util.Date();
            java.sql.Date dataInsert = new java.sql.Date(dataAtual.getTime());
            

        	/*if (paciente.getData_nascimento_paciente() != null) {
          	data_recebida = paciente.getData_nascimento_paciente();
          	dataInsert = new java.sql.Date(data_recebida.getTime());

        	}*/
          	
            pstmt.setString(1, registroDiario.getDescricao_registro());
            pstmt.setInt(2, pacienteDAO.retornaIdPaciente(documento_paciente));
            pstmt.setInt(3, registroDiario.getHumor());
            pstmt.setDate(4, dataInsert);
            pstmt.execute();
            pstmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	
    	
    }
	

}
