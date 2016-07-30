import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import connectoin.ConnectionManager;

public class ConnectionTest {
	private final static String FILE_PATH = "/db_conn_params.xml";

	@Test
	public void testGetDBParams() {

		try {
			Assert.assertNotEquals(null,
					new ConnectionManager().getDBParams(FILE_PATH));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
