/*
  This java coding will test the SSL connection and the msg can not be read by Wireshark because being encrypted.
  If the HTTPS_PORT is port 80, the data can be captured by Wireshark.
  The connection will be between EchoServer.java and EchoClient.java

  Mohammad Ariff Bin Idris (2017430762)
*/
package echoclient;

import java.io.*;
import javax.net.ssl.*;

public class EchoClient {

 public static final boolean DEBUG = true;
 public static final int HTTPS_PORT = 443;//8282
 public static final String HTTPS_HOST = "localhost";//localhost
 public static final String TRUSTTORE_LOCATION = "C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\secAriff.jks";

 public static void main(String[] args) {

 System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);

 if (DEBUG)System.setProperty("javax.net.debug", "ssl:record");

 SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
 try {
 SSLSocket c = (SSLSocket) f.createSocket(HTTPS_HOST, HTTPS_PORT);

 c.startHandshake();
 BufferedWriter w = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
 BufferedReader r = new BufferedReader(new InputStreamReader(c.getInputStream()));
 String msg = "Hey, here is client, how are you?";
 w.write(msg, 0, msg.length());
 w.newLine();
 w.flush();

 // now read the socket
 String m = null;
 while ((m = r.readLine()) != null) {
 System.out.println(m);
 }

 } catch (IOException e) {
 System.err.println(e.toString());
 }

 }
}
