/* --------------------------------------------------------------------------------
 * Astrolab General Tools
 * 
 * Ecole Centrale Nantes - Septembre 2015
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin, Q Roques
 * -------------------------------------------------------------------------------- */
package org.centrale.ldap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * SSL Socket Factory
 * @author ECN
 */
public class MySSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory socketFactory;

    /**
     * Main constructor
     */
    public MySSLSocketFactory() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
            socketFactory = ctx.getSocketFactory();
        } catch (KeyManagementException ex) {
            ex.printStackTrace(System.err);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Get instance
     * @return
     */
    public static SocketFactory getDefault() {
        return new MySSLSocketFactory();
    }

    /**
     * Get Cipher suite
     * @return
     */
    @Override
    public String[] getDefaultCipherSuites() {
        return socketFactory.getDefaultCipherSuites();
    }

    /**
     * Supported cippher suite
     * @return
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return socketFactory.getSupportedCipherSuites();
    }

    /**
     * Create socket
     * @param socket
     * @param string
     * @param i
     * @param bln
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(Socket socket, String string, int i, boolean bln) throws IOException {
        return socketFactory.createSocket(socket, string, i, bln);
    }

    /**
     * Create socket
     * @param string
     * @param i
     * @return
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public Socket createSocket(String string, int i) throws IOException, UnknownHostException {
        return socketFactory.createSocket(string, i);
    }

    /**
     * Create socket
     * @param string
     * @param i
     * @param ia
     * @param i1
     * @return
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public Socket createSocket(String string, int i, InetAddress ia, int i1) throws IOException, UnknownHostException {
        return socketFactory.createSocket(string, i, ia, i1);
    }

    /**
     * Create socket
     * @param ia
     * @param i
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(InetAddress ia, int i) throws IOException {
        return socketFactory.createSocket(ia, i);
    }

    /**
     * Create socket
     * @param ia
     * @param i
     * @param ia1
     * @param i1
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(InetAddress ia, int i, InetAddress ia1, int i1) throws IOException {
        return socketFactory.createSocket(ia, i, ia1, i1);
    }
}