package org.jeecg.modules.system.util;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import java.io.IOException;
/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月23日 10:18
 */
public class CurlToJava {
    public static String  doGet(String url,String token) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // Create custom SSLContext
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) {
                        // Trust all certificates
                        return true;
                    }
                }).build();

        // Create HttpClient instance
        // Create HttpClient with custom SSLContext
        HttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        // Create HttpGet request with the URL
        HttpGet httpGet = new HttpGet(url);

        // Add authorization header
        httpGet.setHeader("Authorization", "Bearer " + token);

        // Execute the request
        HttpResponse response = httpClient.execute(httpGet);

        // Get response status
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Status: " + statusCode);




        // Get response body
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);
        System.out.println("httpEntity.getContent():"+entity.getContent());
        System.out.println("httpEntity.getContentEncoding():"+entity.getContentEncoding());
        System.out.println("httpEntity.getContentType():"+entity.getContentType());
        System.out.println("httpEntity.getContentLength():"+entity.getContentLength());
        // Ensure the response body is fully consumed
        EntityUtils.consume(entity);
        return responseBody;
    }
}
