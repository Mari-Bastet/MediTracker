package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;

public class TratamentoDiarioPacienteDAO implements RepositorioTratamentoDiarioPaciente{
	
    Connection conn = new ConnectionFactory().criaConexao();

    public void atualizaRegistroDiarioMed(TratamentoDiarioPaciente tratDiaPaciente, LocalDate dataRegistro) {
    	
    	Date dataRecebida = Date.valueOf(dataRegistro);
    	
    	String sqlUpdate = "update TB_MTC_REG_DIARIO_MEDICAMENTO "
    				     + "set sta_medicamento_tomado = ?  "	
    			         + "where ID_TRAT_MED_PACIENTE = ? "
    			         + "and DATA_REGISTRO_DIARIO_MED = to_date('" + dataRecebida + "','yyyy-mm-dd') ";
    	
    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)){
	    		
	    		int staRemedioTomado = verificaStaRemedio(tratDiaPaciente,dataRegistro);
	    		int novoEstado = 0;
	    		
	    		if( staRemedioTomado == 0) {
	    			novoEstado = 1;
	    			}
	    		
	    		System.out.println(novoEstado);
	    		
	    		pstmt.setInt(1, novoEstado);
	    		pstmt.setInt(2, tratDiaPaciente.getIdTratMedPaciente());
	    		
	    		pstmt.executeQuery();
	    		
	    	}catch(SQLException e) {
	    		
	    		throw new RuntimeException(e.getMessage());
	    		
	    	}
    	}
    
	public void insereRegistroDiarioTratamento(TratamentoDiarioPaciente tratamentoDiarioPaciente) {
		
		String sqlInsert = "insert into TB_MTC_REG_DIARIO_MEDICAMENTO "
				+ "(ID_REGISTRO_DIARIO_MED"
				+ ",DATA_REGISTRO_DIARIO_MED"
				+ ",ID_TRAT_MED_PACIENTE"
				+ ",STA_MEDICAMENTO_TOMADO)"
				+ "values (seq_id_regisro_diario_med.nextval,?,?,?)";
		

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        	
        	
            Date dataRecebida = null;
            
            dataRecebida = Date.valueOf(tratamentoDiarioPaciente.getDataRegistroDiarioMed());   
            
       
            pstmt.setDate(1, dataRecebida);
            pstmt.setInt(2,  tratamentoDiarioPaciente.getIdTratMedPaciente() );
            pstmt.setInt(3, 0);
            pstmt.execute();
            pstmt.close();

        	/*if (paciente.getData_nascimento_paciente() != null) {
          	data_recebida = paciente.getData_nascimento_paciente();
          	dataInsert = new java.sql.Date(data_recebida.getTime());

        	}*/
          	
 
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }	
	}
	
	
	public Boolean verificaExistenciaRegistroDiario(int idTratMedPaciente
			                                       ,LocalDate dataRegistro
			                                       ,String documentoPaciente){
		
		Date dataRecebida = Date.valueOf(dataRegistro);
		
		
		String sqlSelect = "select count(1) COUNT "
				+ "from TB_MTC_TRAT_MED_PACIENTE      TRAT "
				+ ",    TB_MTC_PACIENTE               PACI "
				+ ",    TB_MTC_REG_DIARIO_MEDICAMENTO REGI "
				+ "where PACI.documento_paciente      =  ? "
				+ "AND  PACI.ID_PACIENTE              = TRAT.ID_PACIENTE "
				+ "and  REGI.id_trat_med_paciente     = TRAT.id_trat_med_paciente "
				+ "and  REGI.data_registro_diario_med =  to_date('" + dataRecebida + "','yyyy-mm-dd') "
				+ "and  TRAT.data_inicio_tratamento  <= sysdate "
				+ "and  TRAT.tratamento_ativo         = 1 "
				+ "and  TRAT.ID_TRAT_MED_PACIENTE     = ? ";
			
		try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){
			
			int vCount = 0;
			
			
			pstmt.setString(1,documentoPaciente);
			pstmt.setInt(2, idTratMedPaciente);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 vCount = rs.getInt(1);
				 }
			System.out.println(vCount);

			if(vCount > 0) {
				return true;
				
			}else {
				return false;
				}
			


		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		

	}
	
	
	
	public int verificaStaRemedio(TratamentoDiarioPaciente tratDiaPaciente, LocalDate dataRegistro) {
		
    	Date dataRecebida = Date.valueOf(dataRegistro);
    	int retorno = 0;
    	
    	String sqlSelect = "select sta_medicamento_tomado "
    			 + "from TB_MTC_REG_DIARIO_MEDICAMENTO "
		         + "where ID_TRAT_MED_PACIENTE = ? "
		         + "and DATA_REGISTRO_DIARIO_MED = to_date('" + dataRecebida + "','yyyy-mm-dd') ";
    	
    	
    	try(PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){
    		
    		pstmt.setInt(1,tratDiaPaciente.getIdTratMedPaciente());
    		
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			
    			retorno = rs.getInt("STA_MEDICAMENTO_TOMADO");
    			
    		}
    		
    		
    		
    		return retorno;

    	}catch(SQLException e) {
    		
    		throw new RuntimeException(e.getMessage());
    		
    	}
	}
	
	
	
	public void fecharConexao() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
