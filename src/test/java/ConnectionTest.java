import java.sql.Connection;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connectoin.ConnectionManager;
import connectoin.params.DatabaseServers;

public class ConnectionTest {
	private final static String FILE_PATH = "/db_conn_params.xml";
	ConnectionManager connectionManager = null;
	DatabaseServers dbParams = null;

	@Before
	public void initDB() {
		connectionManager = new ConnectionManager();
		try {
			dbParams = connectionManager.getDBParams(FILE_PATH);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// test reading from xml config file
	@Test
	public void testGetDBParams() {

		try {
			Assert.assertNotNull(new ConnectionManager().getDBParams(FILE_PATH));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// test databases against given parameters in xml config file
	@Test
	public void testDBConnections() {
		Connection mysqlConnection = connectionManager.getConnection(dbParams
				.getEngines().getEngine().get(0));

		Connection oracleConnection = connectionManager.getConnection(dbParams
				.getEngines().getEngine().get(1));

		Assert.assertNotNull(mysqlConnection);
		Assert.assertNotNull(oracleConnection);

	}

}
