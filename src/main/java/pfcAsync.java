import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcAsyncConnection.*;
import com.ptc.pfc.pfcSession.*;
import com.ptc.wfc.wfcSession.WSession;

import java.io.IOException;
import java.util.Properties;


public class pfcAsync {

    public static AsyncConnection connection;
    public static Session session;
    public static Boolean conn = false;
    public static WSession session1;

    public static void ConnectToCreo() {
            try {
                connection = pfcAsyncConnection.AsyncConnection_Connect(null, null, null, 5);
                session = connection.GetSession();
                conn = connection.IsRunning();
                if (connection.IsRunning()) {
                    System.out.println("Connected");
                }
            } catch (jxthrowable e) {
                throw new RuntimeException(e);
            }
        }

    public static void DisconnectFromCreo() {
        if(connection != null) {
            try {
                connection.Disconnect(2);
                conn = connection.IsRunning();
                System.out.println(connection);
            } catch (jxthrowable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void TestConnection() {
        try {
            connection = pfcAsyncConnection.AsyncConnection_Connect(null, null, null, 5);

        } catch (jxthrowable e) {
            throw new RuntimeException(e);
        }
    }
}


