package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.*;

import java.sql.ResultSet;

public class PacienteDAO {
    Connection conn = new ConnectionFactory().criaConexao();
    
    
    /*public Paciente verificaExistenciaPaciente(String documento_paciente) {
    	
    	aaaa
    	
    }*/
    
    public Paciente realizaLogin(String documento_paciente, String senha_paciente) {
        Paciente paciente = null;

        String sqlSelect = "SELECT ID_PACIENTE ,NOME_PACIENTE ,DATA_NASCIMENTO_PACIENTE ,DOCUMENTO_PACIENTE ,SENHA_PACIENTE,EMAIL_PACIENTE FROM TB_PACIENTE WHERE DOCUMENTO_PACIENTE = ? and SENHA_PACIENTE = ?";


        try {
            PreparedStatement selectPaciente = conn.prepareStatement(sqlSelect);
            selectPaciente.setString(1, documento_paciente);
            selectPaciente.setString(2, senha_paciente);

            ResultSet rs = selectPaciente.executeQuery();

            while (rs.next()) {
            	
            	paciente = new Paciente (rs.getInt("ID_PACIENTE")
										 ,rs.getString("NOME_PACIENTE")
										 ,rs.getDate("DATA_NASCIMENTO_PACIENTE")
										 ,rs.getString("DOCUMENTO_PACIENTE")
										 ,rs.getString("SENHA_PACIENTE")
										 ,rs.getString("EMAIL_PACIENTE")
            			);
            	
            }
            
                       
            
            selectPaciente.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Não foi possível encontrar o paciente");
        }

        return paciente;
    }

	public void fechaConexao() {
		
		try {
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	

}
