package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.ImplementaTratamentoPaciente;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.dominio.TratamentoPaciente;

public class TratamentoPacienteDAO implements ImplementaTratamentoPaciente{
	
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
            System.out.println("entrou1");

            pstmt.setDate(2,dataRecebida);
            System.out.println("entrou2");

            pstmt.setInt(3, tratamentoPaciente.getIdMedicamento());
            
            System.out.println("entrou3");

            pstmt.setInt(4, tratamentoPaciente.getIdDoenca());
            
            System.out.println("entrou4");

            pstmt.setInt(5,pacienteDAO.retornaIdPaciente(documentoPaciente));
            
            System.out.println("entrou5");


            pstmt.execute();
            pstmt.close();

 
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }	
	}
	
	
	public ArrayList<TratamentoPaciente> listaTratamentosDoDia(String documentoPaciente, LocalDate dataRegistro){
		
		ArrayList<TratamentoPaciente> tratsPaciente = new ArrayList<>();
		
		
		Date dataRecebida = Date.valueOf(dataRegistro);
		
		String sqlSelect = "select TRAT.ID_TRAT_MED_PACIENTE "
				+ ",               QUANTIDADE_MEDICAMENTO "
				+ ",               MEDI.NOME_MEDICAMENTO "
				+ ",               REGI.STA_MEDICAMENTO_TOMADO "					
				+ "from TB_MTC_TRAT_MED_PACIENTE TRAT "
				+ ",    TB_MTC_PACIENTE  PACI "
				+ ",    TB_MTC_REG_DIARIO_MEDICAMENTO REGI "
				+ ",    TB_MTC_MED_DOSAGEM MEDO "
				+ ",    TB_MTC_MEDICAMENTO MEDI "
				+ "where PACI.documento_paciente         = ? "
				+ "AND   PACI.ID_PACIENTE                 = TRAT.ID_PACIENTE "
				+ "and   REGI.id_trat_med_paciente(+)     = TRAT.id_trat_med_paciente "
				+ "and   REGI.data_registro_diario_med(+) = to_date('" + dataRecebida + "','yyyy-mm-dd') "
				+ "and   TRAT.data_inicio_tratamento     <= sysdate "
				+ "and   TRAT.tratamento_ativo = 1 "
				+ "and   TRAT.ID_MED_DOSAGEM = MEDO.ID_MED_DOSAGEM "
				+ "and   MEDO.ID_MEDICAMENTO = MEDI.ID_MEDICAMENTO ";
		
		
			
		try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)){

			
			pstmt.setString(1,documentoPaciente);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TratamentoPaciente tratPaciente = new TratamentoPaciente();
				
				tratPaciente.setQuantidadeMedicamento(rs.getDouble("QUANTIDADE_MEDICAMENTO"));
				tratPaciente.setIdtratMedPaciente(rs.getInt("ID_TRAT_MED_PACIENTE"));
				tratPaciente.setNomeMedicamento(rs.getString("NOME_MEDICAMENTO"));
				tratPaciente.setStaMedicamentoTomado(rs.getInt("STA_MEDICAMENTO_TOMADO"));
				
				tratsPaciente.add(tratPaciente);
			}
			
			
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage());
		}
		
		return tratsPaciente;

	}
	
		
	public void fecharConexao() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	

	}
	
}
