package br.com.health.infra.dao.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import br.com.meditracker.infra.dao.ConnectionFactory;

public class ConnectionFactoryTest {
	
	@Test
	public void testeConexao() {
		
		try {
		Connection conn = new ConnectionFactory().criaConexao();
		
		}catch(RuntimeException e) {
			e.printStackTrace();
			
		}
	}

}
