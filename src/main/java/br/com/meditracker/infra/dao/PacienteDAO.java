package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.meditracker.dominio.RepositorioPacientes;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.*;

import java.sql.ResultSet;

public class PacienteDAO implements RepositorioPacientes{
    Connection conn = new ConnectionFactory().criaConexao();
    
    
    public int retornaIdPaciente(String documentoPaciente) {
    String selectQuery = "select id_paciente from tb_mtc_paciente where documento_paciente = ?";
    int idPaciente = 0; 
    try{
        System.out.println(selectQuery);
    PreparedStatement execSelect = conn.prepareStatement(selectQuery);
    
    execSelect.setString(1,documentoPaciente);
    
    ResultSet rs = execSelect.executeQuery();
    
    while(rs.next()) {
    	idPaciente = (rs.getInt("ID_PACIENTE"));
    }
    
    return idPaciente;
    
    }catch(SQLException e) {
        throw new RuntimeException(e);
        
    }
    
}
    
    public void cadastraPaciente(Paciente paciente) {
    	
		String sql = "INSERT INTO TB_MTC_PACIENTE (ID_PACIENTE "
				+ ", NOME_PACIENTE "
				+ ", DATA_NASCIMENTO_PACIENTE "
				+ ", DOCUMENTO_PACIENTE "
				+ ", SENHA_PACIENTE "
				+ ", EMAIL_PACIENTE)"
				+ "  VALUES (SEQ_ID_PACIENTE.NEXTVAL,?,?,?,?,?)";
    
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	
            	Date dataRecebida = Date.valueOf(paciente.getDataNascimentoPaciente());
            	
            	
                pstmt.setString(1, paciente.getNomePaciente());
                pstmt.setDate(2, dataRecebida);
                pstmt.setString(3, paciente.getDocumentoPaciente());
                pstmt.setString(4, paciente.getSenhaPaciente());
                pstmt.setString(5, paciente.getEmailPaciente());
                pstmt.execute();
                pstmt.close();
                
            } catch (SQLException e) {
            	
                throw new RuntimeException(e);
            }
        		 	
    }
    
    public Paciente realizaLogin(String documentoPaciente, String senhaPaciente) {
    	
        Paciente paciente = null;

        String sqlSelect = "SELECT ID_PACIENTE "
        		+ "			,NOME_PACIENTE "
        		+ "         ,DATA_NASCIMENTO_PACIENTE "
        		+ "         ,DOCUMENTO_PACIENTE "
        		+ "         ,SENHA_PACIENTE,EMAIL_PACIENTE "
        		+ "          FROM TB_MTC_PACIENTE "
        		+ "          WHERE DOCUMENTO_PACIENTE = ? "
        		+ "          and SENHA_PACIENTE = ?";

        try {
        	
            PreparedStatement selectPaciente = conn.prepareStatement(sqlSelect);
            selectPaciente.setString(1, documentoPaciente);
            selectPaciente.setString(2, senhaPaciente);

            ResultSet rs = selectPaciente.executeQuery();

            while (rs.next()) {
            	
            	int idPaciente 		= rs.getInt("ID_PACIENTE");
            	String nomePaciente = rs.getString("NOME_PACIENTE");
              	Date dataNascimentoPaciente = rs.getDate("DATA_NASCIMENTO_PACIENTE");
              	LocalDate dataSelect = dataNascimentoPaciente.toLocalDate();
                String emailPaciente = rs.getString("EMAIL_PACIENTE");
                
                paciente = new Paciente(idPaciente,nomePaciente,dataSelect,documentoPaciente,senhaPaciente,emailPaciente);

            }
           
            selectPaciente.close();
            
        	} catch (SQLException e) {
        		throw new RuntimeException(e.getMessage());
        }

        return paciente;
    }


	public void fecharConexao() {
		
		try {
			
			conn.close();
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e.getMessage());
		}
	}

	

}
 