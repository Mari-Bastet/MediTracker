
package br.com.meditracker.infra.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.meditracker.dominio.RepositorioRelatorio;

import java.io.File;

public class RelatorioTratamentoPacienteDAO implements RepositorioRelatorio{
	
    Connection conn = new ConnectionFactory().criaConexao();
	
	public void geraPdf(String documentoPaciente) {
		
		String filename = "relatorioTratamento.pdf";
		
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		String desktop = fileSystemView.getHomeDirectory().getAbsolutePath();
		
		String caminhoDoArquivo = desktop + "//" + filename;
		
		
		String sqlSelect = "select doen.nome_doenca "
				+ "				          ,      medi.nome_medicamento "
				+ "				          ,      trmp.quantidade_medicamento "
				+ "				          ,      trmp.data_inicio_tratamento "
				+ "				          ,      redm.data_registro_diario_med data_registro "
				+ "				          ,      'Medicamento tomado? '|| case when redm.sta_medicamento_tomado = 0 then 'NÃO' "
				+ "				                                               when redm.sta_medicamento_tomado = 1 then 'SIM' "
				+ "				                                               else null end medicamento_tomado "
				+ "				          ,     redm.id_registro_diario_med  "
				+ "				          ,     paci.id_paciente "
				+ "				          from tb_mtc_trat_med_paciente trmp "
				+ "				          ,    tb_mtc_reg_diario_medicamento redm "
				+ "				          ,    tb_mtc_paciente paci "
				+ "				          ,    tb_mtc_doenca doen "
				+ "				          ,    tb_mtc_med_dosagem medo "
				+ "				          ,    tb_mtc_medicamento medi "
				+ "				          where trmp.id_paciente          = paci.id_paciente "
				+ "				          and   trmp.id_trat_med_paciente = redm.id_trat_med_paciente "
				+ "				          and   doen.id_doenca            = trmp.id_doenca "
				+ "				          and   medo.id_medicamento       = medi.id_medicamento "
				+ "				          and   medo.id_med_dosagem       = trmp.id_med_dosagem "
				+ "						  and    paci.documento_paciente  = ? "
				+ "				          order by trmp.id_trat_med_paciente";

		Document doc = new Document();
		
		try {				
				PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
				
				pstmt.setString(1, documentoPaciente);
				ResultSet rs = pstmt.executeQuery();
				
				PdfWriter.getInstance(doc, new FileOutputStream(caminhoDoArquivo));
				doc.open();

				
				PdfPTable colunas = new PdfPTable(3);
				PdfPCell linhas;
				
				
				Paragraph para = new Paragraph("Documento do Paciente: " + documentoPaciente); 
				doc.add(para);
				
				while(rs.next()) {
					
					String nomeMedicamento = rs.getString("NOME_MEDICAMENTO");
					linhas = new PdfPCell(new Phrase(nomeMedicamento));
					colunas.addCell(linhas);
					
					
					Date dataRegistro = rs.getDate("DATA_REGISTRO");
					linhas = new PdfPCell(new Phrase(dataRegistro.toString()));
					colunas.addCell(linhas);
					
					String medicamentoTomado = rs.getString("MEDICAMENTO_TOMADO");
					linhas = new PdfPCell(new Phrase(medicamentoTomado));
					colunas.addCell(linhas);
	
				}
				
				
				doc.add(colunas);
				doc.close();

			
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	
	}

}
