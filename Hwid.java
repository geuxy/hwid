package me.geuxy;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.security.MessageDigest;
import java.util.Scanner;

/** Hwid Util
 * @author NapoleonZoomberparts
 */
public class Hwid {

    public boolean isWhitelisted() {
        try {
            String url = "insert url here";
            String s = (new Scanner((new URL(url)).openStream(), "UTF-8")).useDelimiter("\\A").next();
            return s.contains(getHwid());
        } catch (Exception e) {
            return false;
        }
    }

    public String getHwid() {
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            String s = String.valueOf(System.getProperty("os.name")) + System.getProperty("os.arch") + System.getProperty("os.version") + Runtime.getRuntime().availableProcessors() + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS");
            return bytesToHex(hash.digest(s.getBytes()));
        } catch (Exception e) {
            return "######################";
        }
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = "0123456789ABCDEF".toCharArray()[v >>> 4];
            hexChars[j * 2 + 1] = "0123456789ABCDEF".toCharArray()[v & 0xF];
        }
        return new String(hexChars);
    }
}
