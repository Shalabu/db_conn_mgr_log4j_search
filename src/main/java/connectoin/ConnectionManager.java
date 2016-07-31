package connectoin;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import connectoin.params.DatabaseServers;
import connectoin.params.DatabaseServers.Engines.Engine;

public class ConnectionManager {


	ConnectionPoolDataSource pooledDataSource = null;
	PooledConnection pooledConnection = null;

	public Connection getConnection(Engine dbEngine) {

		String engineType = dbEngine.getName();

		// *******************connection parameters*********************//
		String userName = dbEngine.getUser();
		String password = dbEngine.getPassword();
		String host = dbEngine.getHost();
		String schema = dbEngine.getSchema();
		int port = Integer.valueOf(dbEngine.getPort());
		String instance = dbEngine.getInstance();
		String protocol = dbEngine.getProtocol();
		String driverType = dbEngine.getDrivertype();

		switch (engineType) {
		case "ORACLE_THIN":

			try {
				pooledDataSource = new OracleConnectionPoolDataSource();
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setDriverType(driverType);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setNetworkProtocol(protocol);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setServerName(host);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setServiceName(instance);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setPortNumber(port);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setUser(userName);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setPassword(password);
				((OracleConnectionPoolDataSource) pooledDataSource)
						.setDatabaseName(schema);

				pooledConnection = pooledDataSource.getPooledConnection();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case "MYSQL":

			try {
				pooledDataSource = new MysqlConnectionPoolDataSource();
				((MysqlConnectionPoolDataSource) pooledDataSource)
						.setServerName(host);
				((MysqlConnectionPoolDataSource) pooledDataSource)
						.setDatabaseName(instance);
				((MysqlConnectionPoolDataSource) pooledDataSource)
						.setPortNumber(port);
				((MysqlConnectionPoolDataSource) pooledDataSource)
						.setUser(userName);
				((MysqlConnectionPoolDataSource) pooledDataSource)
						.setPassword(password);

				pooledConnection = pooledDataSource.getPooledConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		}

		try {
			return pooledConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	// read database engines from xml file JAXB is used
	public DatabaseServers getDBParams(String xmlParamFile)
			throws JAXBException {

		DatabaseServers servers = null;
		File xmlFile = new File(ConnectionManager.class.getResource(
				xmlParamFile).getFile());

		JAXBContext jaxbContext = JAXBContext
				.newInstance(DatabaseServers.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		servers = (DatabaseServers) jaxbUnmarshaller.unmarshal(xmlFile);
		return servers;

	}

}
