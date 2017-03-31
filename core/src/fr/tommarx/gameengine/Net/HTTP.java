package fr.tommarx.gameengine.Net;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class HTTP {

    public static void download(String _url, String dest, HTTPListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(_url);
                HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                long completeFileSize = httpConnection.getContentLength();

                java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                java.io.FileOutputStream fos = new java.io.FileOutputStream(
                        dest);
                BufferedOutputStream bout = new BufferedOutputStream(
                        fos, 1024);
                byte[] data = new byte[1024];
                long downloadedFileSize = 0;
                int x = 0;
                while ((x = in.read(data, 0, 1024)) >= 0) {
                    downloadedFileSize += x;
                    final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
                    listener.onProgress(currentProgress);
                    bout.write(data, 0, x);
                }
                bout.close();
                in.close();
                listener.onFinish(new File(dest).getAbsolutePath());
                Thread.currentThread().interrupt();
            } catch (FileNotFoundException e) {
                listener.onFail("File not found ! (" + dest + ")");
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                listener.onFail("IOException");
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void get(String _url, HTTPListener listener) {
        new Thread(() -> {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int contentLength = conn.getContentLength();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            int readBytes = 0;
            while ((line = rd.readLine()) != null) {
                result.append(line);
                if (contentLength != -1) {
                    readBytes += line.getBytes("ISO-8859-2").length + 2;
                    listener.onProgress((readBytes / contentLength) * 100);
                }
            }
            rd.close();
            listener.onFinish(result.toString());
            Thread.currentThread().interrupt();
        } catch (ProtocolException e) {
            e.printStackTrace();
            listener.onFail("Protocol");
            Thread.currentThread().interrupt();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            listener.onFail("URL");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            listener.onFail("IOException");
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        }).start();
    }

    public static void post(String _url, Map<String, String> args, HTTPListener listener) {

        new Thread(() -> {
        try {
            String data = "";
            for (Map.Entry<String, String> entry : args.entrySet()) {
                data += entry.getKey() + "=" + entry.getValue() + "&";
            }
            if (data.length() > 0){
                data = data.substring(0, data.length()-1);
            }
            StringBuilder result = new StringBuilder();
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.addRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            int contentLength = conn.getContentLength();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            int readBytes = 0;
            while ((line = rd.readLine()) != null) {
                result.append(line);
                if (contentLength != -1) {
                    readBytes += line.getBytes("ISO-8859-2").length + 2;
                    listener.onProgress((readBytes / contentLength) * 100);
                }
            }
            wr.close();
            rd.close();
            listener.onFinish(result.toString());
            Thread.currentThread().interrupt();
        } catch (ProtocolException e) {
            e.printStackTrace();
            listener.onFail("Protocol");
            Thread.currentThread().interrupt();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            listener.onFail("URL");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            listener.onFail("IOException");
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        }).start();
    }


}
