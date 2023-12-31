package com.atifa.TollManagementAPI;

import base.AccessToken;
import base.Base;
import base.PayloadProcessor;
import io.restassured.response.Response;
import utility.GetCurrentDate;
import utility.URL;

import java.util.Map;
import java.util.Properties;

public class LogIn {
    public static Response login(){
        String bearerToken="Bearer "+ AccessToken.getToken();
        String url= URL.getEndPoint("/cognito-login");
        String jsonBody= Base.generatePayLoadString("logIn.json");

        Response response=Base.POSTRequest(url,jsonBody,bearerToken);
        return response;

    }
    public static Response loginInvalidUser(){
        String bearerToken="Bearer "+ AccessToken.getToken();
        String url= URL.getEndPoint("/cognito-login");

        Properties properties=new Properties();
        Map<String,Object> payloadInMap= PayloadProcessor.getProcessedPayloadInJson("login.json",properties);
        String user="Manager"+GetCurrentDate.getCurrentDate();
        payloadInMap.put("username",user);

        String payloadInString=PayloadProcessor.payloadFromMapToString(payloadInMap);


        Response response=Base.POSTRequest(url,payloadInString,bearerToken);
        return response;

    }
    public static Response loginInvalidPassword(){
        String bearerToken="Bearer "+ AccessToken.getToken();
        String url= URL.getEndPoint("/cognito-login");

        Properties properties=new Properties();
        Map<String,Object> payloadInMap= PayloadProcessor.getProcessedPayloadInJson("login.json",properties);
        String password="Manager@"+ GetCurrentDate.getCurrentTimeStamp();
        payloadInMap.put("password",password);

        String payloadInString=PayloadProcessor.payloadFromMapToString(payloadInMap);


        Response response=Base.POSTRequest(url,payloadInString,bearerToken);
        return response;

    }


}
