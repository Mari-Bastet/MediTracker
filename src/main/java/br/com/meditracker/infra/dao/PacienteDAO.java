package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.meditracker.dominio.ImplementaPaciente;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.*;

import java.sql.ResultSet;

public class PacienteDAO{
    Connection conn = new ConnectionFactory().criaConexao();
    
    
    public int retornaIdPaciente(String documento_paciente) {
    String selectQuery = "select id_paciente from tb_paciente where documento_paciente = ?";
    int id_paciente = 0; 
    try{
        
    PreparedStatement execSelect = conn.prepareStatement(selectQuery);
    
    execSelect.setString(1,documento_paciente);
    
    ResultSet rs = execSelect.executeQuery();
    
    while(rs.next()) {
    id_paciente = (rs.getInt("ID_PACIENTE"));
    }
    
    return id_paciente;
    
    }catch(SQLException e) {
        throw new RuntimeException(e);
        
    }
    
}
    
    public void cadastraPaciente(Paciente paciente) {
    	
		String sql = "INSERT INTO TB_PACIENTE (ID_PACIENTE , NOME_PACIENTE , DATA_NASCIMENTO_PACIENTE , DOCUMENTO_PACIENTE , SENHA_PACIENTE , EMAIL_PACIENTE)VALUES (SEQ_ID_PACIENTE.NEXTVAL,?,?,?,?,?)";
    	
    
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	java.util.Date data_recebida = null;
            	java.sql.Date  dataInsert = null;
            	
            	if (paciente.getData_nascimento_paciente() != null) {
              	data_recebida = paciente.getData_nascimento_paciente();
              	dataInsert = new java.sql.Date(data_recebida.getTime());

            	}
              	
                pstmt.setString(1, paciente.getNome_paciente());
                pstmt.setDate(2, dataInsert);
                pstmt.setString(3, paciente.getDocumento_paciente());
                pstmt.setString(4, paciente.getSenha_paciente());
                pstmt.setString(5, paciente.getEmail_paciente());
                pstmt.execute();
                pstmt.close();
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        		 	
    }
    
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
            	
            	
            	int id_paciente = rs.getInt("ID_PACIENTE");
            	String nome_paciente = rs.getString("NOME_PACIENTE");
            	
              	java.util.Date data_nascimento_paciente = rs.getDate("DATA_NASCIMENTO_PACIENTE");
            	java.sql.Date  data_select = new java.sql.Date(data_nascimento_paciente.getTime());
            	
      
                //Date data_nascimento_paciente = rs.getDate("DATA_NASCIMENTO_PACIENTE");
                //LocalDate data_nascimento_paciente = (dataNascimentoConv != null) ? dataNascimentoConv.toLocalDate() : null;
                String email_paciente = rs.getString("EMAIL_PACIENTE");
                
                paciente = new Paciente(id_paciente,nome_paciente,data_select,documento_paciente,senha_paciente,email_paciente);
            	
            	/*paciente = new Paciente (rs.getInt("ID_PACIENTE")
										 ,rs.getString("NOME_PACIENTE")
										 ,rs.getDate("DATA_NASCIMENTO_PACIENTE")
										 ,rs.getString("DOCUMENTO_PACIENTE")
										 ,rs.getString("SENHA_PACIENTE")
										 ,rs.getString("EMAIL_PACIENTE")
            			);
            	*/
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
			e.printStackTrace();
		}
	}

	

}
