package com.java.ghmall.utils;

import com.google.gson.Gson;
import com.java.ghmall.form.ImgurResForm;
import okhttp3.RequestBody;
import org.apache.commons.net.util.Base64;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public interface IMGUtil {

    public static final String AUTH = "6676c6a29041d49";
    public static final String uri = "https://api.imgur.com/3/image";

    public static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

   public static String upload2Imgur(String filePath) throws IOException {
       MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

//       bodyMap.add("image", getUserFileResource(fileBytes));
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.MULTIPART_FORM_DATA);
       headers.add("Authorization", "Client-ID "+AUTH);
       HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<ImgurResForm> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ImgurResForm.class);
       System.out.println("response status: " + response.getStatusCode()); // it should return 200
       System.out.println("response body: " + response.getBody().getData().getLink()); // it should return link of your uploaded image

       return response.getBody().getData().getLink();
   }
    public static Resource getUserFileResource(byte[] bytes) throws IOException {
        return new ByteArrayResource(bytes);
    }
}