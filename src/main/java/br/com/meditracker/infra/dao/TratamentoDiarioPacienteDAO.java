package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;

public class TratamentoDiarioPacienteDAO {
	
    Connection conn = new ConnectionFactory().criaConexao();

    
    
    public void atualizaRegistroDiario(TratamentoDiarioPaciente tratamentoDiarioPaciente) {
    	
    	String sqlUpdate = "update TB_MTC_REG_DIARIO_MEDICAMENTO where idRegistroTratMed = ?";
    	}
	public void insereRegistroDiarioTratamento(TratamentoDiarioPaciente tratamentoDiarioPaciente) {
		
		String sqlInsert = "insert into TB_MTC_REG_DIARIO_MEDICAMENTO "
				+ "(ID_REGISTRO_DIARIO_MED"
				+ ",DATA_REGISTRO_DIARIO_MED"
				+ ",ID_TRAT_MED_PACIENTE"
				+ ",STA_MEDICO_TOMADO)"
				+ "values (seq_id_regisro_diario_med.nextval,?,?,?)";
		
        PacienteDAO pacienteDAO = new PacienteDAO();

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        	
        	
        	//System.out.println(tratamentoPaciente.getQuantidadeMedicamento());
            Date dataRecebida = null;
            
            dataRecebida = Date.valueOf(tratamentoDiarioPaciente.getDataRegistroDiarioMed());   
            
       
            pstmt.setDate(1, dataRecebida);
            pstmt.setInt(2,tratamentoDiarioPaciente.getIdTratMedPaciente());
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
		
		
		String sqlSelect = "select count(1) COUNT "
				+ "from TB_MTC_TRAT_MED_PACIENTE      TRAT "
				+ ",    TB_MTC_PACIENTE               PACI "
				+ ",    TB_MTC_REG_DIARIO_MEDICAMENTO REGI "
				+ "where PACI.documento_paciente      =  ?"
				+ "AND  PACI.ID_PACIENTE              = TRAT.ID_PACIENTE "
				+ "and  REGI.id_trat_med_paciente     = TRAT.id_trat_med_paciente "
				+ "and  REGI.data_registro_diario_med = ? "
				+ "and  TRAT.data_inicio_tratamento  <= sysdate "
				+ "and  TRAT.tratamento_ativo         = 1"
				+ "and  TRAT.ID_TRAT_MED_PACIENTE     = ?";
			
		try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){
			
			int vCount = 0;
			
			Date dataRecebida = Date.valueOf(dataRegistro);
			
			pstmt.setString(1,documentoPaciente);
			pstmt.setDate(2, dataRecebida);
			pstmt.setDate(0, dataRecebida);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 vCount = rs.getInt("COUNT");
				 }

			if(vCount > 1) {
				return true;
				
			}else {
				return false;
				}

		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		

	}
	

	}
