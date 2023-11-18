package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.dominio.TratamentoPaciente;

public class TratamentoPacienteDAO {
	
    Connection conn = new ConnectionFactory().criaConexao();

	public void insereTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		String sqlInsert = "insert into TB_MTC_TRAT_MED_PACIENTE (id_trat_med_paciente,quantidade_medicamento,data_inicio_tratamento,id_med_dosagem,id_doenca,id_paciente,tratamento_ativo,data_termino_tratamento)"
				+ "values (seq_id_trat_med_paciente.nextval,?,?,?,?,?,1,null)";
		
        PacienteDAO pacienteDAO = new PacienteDAO();

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        	
        	
        	//System.out.println(tratamentoPaciente.getQuantidadeMedicamento());
            Date dataRecebida = null;
            
            dataRecebida = Date.valueOf(tratamentoPaciente.getDataInicioTratamento());   
            
       
            pstmt.setDouble(1, tratamentoPaciente.getQuantidadeMedicamento());
            pstmt.setDate(2,dataRecebida);
            pstmt.setInt(3, tratamentoPaciente.getIdMedicamento());
            pstmt.setInt(4, tratamentoPaciente.getIdDoenca());
            pstmt.setInt(5,pacienteDAO.retornaIdPaciente(documentoPaciente));
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
	
	
	public ArrayList<TratamentoPaciente> listaTratamentosDoDia(){
		ArrayList<TratamentoPaciente> tratsPaciente = new ArrayList<>();
		
		
		String sqlSelect = "select trat.* "
				+ "from TB_MTC_TRAT_MED_PACIENTE TRAT "
				+ ",    TB_MTC_PACIENTE  PACI "
				+ ",    TB_MTC_REGISTRO_DIARIO_MEDICAMENTO REGI "
				+ "where PACI.id_paciente                =  5 "
				+ "AND   PACI.ID_PACIENTE                = TRAT.ID_PACIENTE "
				+ "and   REGI.id_trat_med_paciente(+)    = TRAT.id_trat_med_paciente "
				+ "and  REGI.data_registro_diario_med(+) = sysdate "
				+ "and  TRAT.data_inicio_tratamento     <= sysdate "
				+ "and  TRAT.tratamento_ativo = 1";
			
		try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TratamentoPaciente tratPaciente = new TratamentoPaciente();
				//tratPaciente.setQuantidadeMedicamento(rs.getDouble("QUANTIDADE_MEDICAMENTO"));

				//tratPaciente.setQuantidadeMedicamento(rs.getDouble("QUANTIDADE_MEDICAMENTO"));
				//tratPaciente.setNomeMedicamento(rs.getString("NOME_MEDICAMENTO"));
				
				tratsPaciente.add(tratPaciente);
			}
			
			
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		
		return tratsPaciente;

	}
	

	}
