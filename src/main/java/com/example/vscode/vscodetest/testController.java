package com.example.vscode.vscodetest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    
    private final String SITE_IS_UP = "Site is up";
    private final String SITE_IS_NOT_UP = "Site is not up";
    private final String URL_IS_INCORRECT = "The url is incorrent";

    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url){
        
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if(responseCodeCategory != 2 || responseCodeCategory != 3){
                returnMessage = SITE_IS_NOT_UP;
            }else{
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            return returnMessage = URL_IS_INCORRECT;
        } catch (IOException e) {
            return returnMessage = SITE_IS_NOT_UP;
        }
        return returnMessage;
    } 


}
