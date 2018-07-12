package com.hackathon.hackathon.framework;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hackathon.hackathon.application.App;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for Network status
 */
public class NetworkUtil {
    /**
     * This method will give you the Network status..
     *
     * @return : Network Status
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null && cm.getActiveNetworkInfo() != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }


    /**
     * Getting local ip address of the device - partial address ( first 2 octant)
     *
     * @return
     */
    public static String getLocalIpAddress() {
        return getLocalIpAddress(false);
    }

    /**
     * Getting local ip address of the device
     *
     * @return : IP address of the device
     */
    public static String getLocalIpAddress(boolean fullAddress) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;
                        if (isIPv4) {
                            String[] addressIp = sAddr.split("\\.");
                            String ip = addressIp[0] + "." + addressIp[1];
                            if (fullAddress) {
                                ip += "." + addressIp[2] + "." + addressIp[3];
                            }
                            Log.d("Ip:", "ip address:" + ip);
                            return ip;
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return "";
    }
}






