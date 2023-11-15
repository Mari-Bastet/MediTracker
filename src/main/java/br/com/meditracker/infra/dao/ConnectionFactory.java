package br.com.meditracker.infra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection criaConexao() {
		
		String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		String log = "rm97510";
		String senha = "011003";
		
		try {
			return DriverManager.getConnection(url,log,senha);
			
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
			
		}
		

	}

}
