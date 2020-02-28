/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.items.Connexion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.centrale.pgrou.saturne.repositories.ConnexionRepository;

/**
 *
 * @author ECN
 */
public class Security {

    private static final String[] HEADERS_TO_TRY = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"};

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
    
    /**
     * Remove old connections
     *
     * @param connectionRepository
     * @return
     */
    public static boolean check(ConnexionRepository connectionRepository) {
        connectionRepository.removeOld();
        return false;
    }
    
    /**
     * Check connexion and remove old connections
     *
     * @param connectionRepository
     * @param code
     * @return
     */
    public static boolean check(ConnexionRepository connectionRepository, String code) {
        return (getConnexion(connectionRepository, code) != null);
    }
    
    /**
     * get connexion and remove old connections
     *
     * @param connectionRepository
     * @param code
     * @return
     */
    public static Connexion getConnexion(ConnexionRepository connectionRepository, String code) {
        Connexion returnedValue = null;

        connectionRepository.removeOld();
        if ((code != null) && (!code.isEmpty())) {
            Optional<Connexion> result = connectionRepository.findById(code);
            if (result.isPresent()) {
                returnedValue = result.get();
                connectionRepository.expand(returnedValue);
            }
        }

        return returnedValue;
    }
    
    public static String encryptPassword(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(pass);
        return encoded;
    }

    public static boolean validatePassword(String origin, String encrypted) {
        if ((origin == null) || (encrypted == null)) {
            return false;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(origin, encrypted);
        }
    }
}
