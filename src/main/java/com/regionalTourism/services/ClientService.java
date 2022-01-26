package com.regionalTourism.services;

import com.google.gson.JsonObject;
import com.regionalTourism.commons.Constants;
import com.regionalTourism.commons.GeneralUtils;
import com.regionalTourism.dto.Client;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@CommonsLog
@Service
public class ClientService {

    @Autowired
    private final SqlSession sqlSession;

    public ClientService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public ResponseEntity createClient(String documentClient,String firstName,String lastName, Integer age, String email){
        JsonObject response = new JsonObject();
            if(documentClient ==null || firstName ==null || lastName==null || age==null || email==null ){
                ResponseEntity<Integer> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            try{
                Client client = new Client();
                client.setDocumentClient(documentClient);
                client.setFirstName(firstName);
                client.setLastName(lastName);
                client.setAge(age);
                client.setEmail(email);
                client.setStatus(1);

                this.sqlSession.insert("insertClient", client);
                response.add("data", Constants.GSON.toJsonTree(client));
                response.addProperty("status", "success");
                response.addProperty("message", "Successfully Registered");
            }catch (Exception e){
                ResponseEntity<Integer> responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
                return responseEntity;
            }
        return GeneralUtils.createOkResponse(response.toString());

    }
}
