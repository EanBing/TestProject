package com.haloasis.testproject.Service;

import com.google.gson.JsonObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/httppost")
public class HttpPostService {

    @RequestMapping("/testHttpPost")
    public Map<String, Object> testHttpPost() throws IOException {
        Map<String, Object> retMap = null;

        String url = "http://localhost:8081/lottery/area/testHttpPost";
        HttpPost httpPost = new HttpPost(url);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("test", "test");
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        stringEntity.setContentEncoding("utf-8");
        stringEntity.setContentType("application/json");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000)
                .build();
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println("responseBody: ".concat(responseBody));
        System.out.println(response);

        return retMap;
    }

}
