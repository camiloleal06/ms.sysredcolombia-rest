package ms.sysredcolombia.rest.mikrotik;

import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;

import javax.net.SocketFactory;

/**
 *
 */
abstract class Conectar {

    protected void connect() throws MikrotikApiException {
        con = ApiConnection.connect(SocketFactory.getDefault(),
                ConfigMikrotik.HOST, ApiConnection.DEFAULT_PORT, 2000);
        con.login(ConfigMikrotik.USERNAME, ConfigMikrotik.PASSWORD);

    }

    protected void disconnect() throws ApiConnectionException {
        con.close();
    }

    protected ApiConnection con;

}