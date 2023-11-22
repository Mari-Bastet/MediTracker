
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
		
		
		String sqlSelect = "select * from (with trat_med as (select doen.nome_doenca "
				+ "          ,      medi.nome_medicamento "
				+ "          ,      trmp.quantidade_medicamento "
				+ "          ,      trmp.data_inicio_tratamento "
				+ "          ,      redm.data_registro_diario_med data_registro "
				+ "          ,      'Medicamento tomado? '|| case when redm.sta_medicamento_tomado = 0 then 'NÃO' "
				+ "                                               when redm.sta_medicamento_tomado = 1 then 'SIM' "
				+ "                                               else null end medicamento_tomado "
				+ "          ,     redm.id_registro_diario_med  "
				+ "          ,     paci.id_paciente "
				+ "          from tb_mtc_trat_med_paciente trmp "
				+ "          ,    tb_mtc_reg_diario_medicamento redm "
				+ "          ,    tb_mtc_paciente paci "
				+ "          ,    tb_mtc_doenca doen "
				+ "          ,    tb_mtc_med_dosagem medo "
				+ "          ,    tb_mtc_medicamento medi "
				+ "          where trmp.id_paciente          = paci.id_paciente "
				+ "          and   trmp.id_trat_med_paciente = redm.id_trat_med_paciente "
				+ "          and   doen.id_doenca            = trmp.id_doenca "
				+ "          and   medo.id_medicamento       = medi.id_medicamento "
				+ "          and   medo.id_med_dosagem       = trmp.id_med_dosagem "
				+ "          order by trmp.id_trat_med_paciente) "
				+ " "
				+ ", trat_dia as (select distinct paci.id_paciente "
				+ "            ,      redi.data_registro  "
				+ "            ,      redi.id_registro_diario "
				+ "            ,      descricao_registro "
				+ "            ,      case when sidi.id_registro_diario is not null then  "
				+ "                        listagg(sidi.nome_sintoma||' - intensidade : '||sidi.intensidade_sintoma,' ; ')  over (partition by redi.id_registro_diario) "
				+ "                        else null end sintomas "
				+ "            from tb_mtc_paciente paci "
				+ "            ,    tb_mtc_registro_diario redi "
				+ "            ,    tb_mtc_sintoma_diario sidi "
				+ "            where paci.id_paciente        = redi.id_paciente "
				+ "            and   redi.id_registro_diario = sidi.id_registro_diario(+) "
				+ "            group by paci.id_paciente "
				+ "            ,        redi.data_registro  "
				+ "            ,        redi.id_registro_diario "
				+ "            ,        descricao_registro "
				+ "            ,        sidi.nome_sintoma "
				+ "            ,        sidi.intensidade_sintoma "
				+ "            ,        sidi.id_registro_diario "
				+ "            order by paci.id_paciente "
				+ "            ,        redi.data_registro) "
				+ " "
				+ "select trme.nome_doenca "
				+ ",      trme.nome_medicamento "
				+ ",      trme.quantidade_medicamento "
				+ ",      trme.data_inicio_tratamento "
				+ ",      trme.data_registro "
				+ ",      trme.medicamento_tomado "
				+ ",      trme.id_registro_diario_med  "
				+ ",      trme.id_paciente "
				+ ",      trdi.id_registro_diario "
				+ ",      trdi.descricao_registro "
				+ ",      trdi.sintomas "
				+ "from trat_med trme "
				+ ",    trat_dia trdi "
				+ "where trme.id_paciente = trdi.id_paciente "
				+ "and   trme.data_registro = trdi.data_registro "
				+ "union  "
				+ "select trme.* "
				+ ",      null id_registro_diario "
				+ ",      null descricao_registro "
				+ ",      null sintomas "
				+ "from  trat_med trme "
				+ "where   trme.data_registro not in (select data_registro  "
				+ "                                   from trat_dia dia  "
				+ "                                   where dia.id_paciente = trme.id_paciente) "
				+ "union "
				+ "    select null nome_doenca "
				+ "    ,      null nome_medicamento "
				+ "    ,      null quantidade_medicamento "
				+ "    ,      null data_inicio_tratamento "
				+ "    ,      null data_registro "
				+ "    ,      null medicamento_tomado "
				+ "    ,      null id_registro_diario_med  "
				+ "    ,      null id_paciente "
				+ "    ,      trme.id_registro_diario "
				+ "    ,      trme.descricao_registro "
				+ "    ,      trme.sintomas "
				+ "from  trat_dia trme "
				+ "where   trme.data_registro not in (select data_registro  "
				+ "                                   from trat_med dia  "
				+ "                                   where dia.id_paciente = trme.id_paciente)) a "
				+ ", tb_mtc_paciente b "
				+ "where a.id_paciente = b.id_paciente "
				+ "and b.documento_paciente = ? ";

		Document doc = new Document();
		
		try {				
				PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
				
				pstmt.setString(1, documentoPaciente);
				ResultSet rs = pstmt.executeQuery();
				
				PdfWriter.getInstance(doc, new FileOutputStream(caminhoDoArquivo));
				doc.open();

				
				PdfPTable colunas = new PdfPTable(5);
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

					String descricaoRegistro = rs.getString("DESCRICAO_REGISTRO");
					linhas = new PdfPCell(new Phrase(descricaoRegistro));
					colunas.addCell(linhas);
					
					String sintomas = rs.getString("SINTOMAS");
					linhas = new PdfPCell(new Phrase(sintomas));
					colunas.addCell(linhas);
	
				}
				

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

					String descricaoRegistro = rs.getString("DESCRICAO_REGISTRO");
					linhas = new PdfPCell(new Phrase(descricaoRegistro));
					colunas.addCell(linhas);
					
					String sintomas = rs.getString("SINTOMAS");
					linhas = new PdfPCell(new Phrase(sintomas));
					colunas.addCell(linhas);
	
				}
				
				doc.add(colunas);
				doc.close();

			
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	
	}

}
