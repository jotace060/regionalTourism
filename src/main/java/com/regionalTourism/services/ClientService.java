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

import java.util.HashMap;

@CommonsLog
@Service
public class ClientService {

    @Autowired
    private final SqlSession sqlSession;

    public ClientService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Client getAllClients(){

        JsonObject response = new JsonObject();
        Client client = this.sqlSession.selectOne("getAllClients",null);
        System.out.println(client);
        response.add("data", Constants.GSON.toJsonTree(client));
        response.addProperty("status", "success");
        response.addProperty("message", "Successfully Registered");

        return  client;

    }

    public ResponseEntity<JsonObject> updateClient(Client client){

        JsonObject response = new JsonObject();
        Client updateClient = new Client();
        try {
            if (client != null) {
                updateClient = this.sqlSession.selectOne("getClientById", client.getIdClient());
                System.out.println("1");

            }
            if (updateClient != null) {
                System.out.println("2");
                sqlSession.update("updateClient", client);
                response.add("data", Constants.GSON.toJsonTree(client));
                response.addProperty("status", "success");
                response.addProperty("message", "Successfully Registered");

            }
        }catch (Exception e){
            e.printStackTrace();
            ResponseEntity<JsonObject> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<JsonObject> createClient(Client clientT){
        JsonObject response = new JsonObject();
            if(clientT ==null  ){
                ResponseEntity<JsonObject> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            try{
                 this.sqlSession.insert("insertClient", clientT);
                response.add("data", Constants.GSON.toJsonTree(clientT));
                response.addProperty("status", "success");
                response.addProperty("message", "Successfully Registered");
            }catch (Exception e){
                ResponseEntity<JsonObject> responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
                return responseEntity;
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    public ResponseEntity deleteLogicClient(Integer idClient){
        JsonObject response = new JsonObject();
        log.info("deleteLogicCliente: " + idClient);
        if(idClient==null){
            ResponseEntity entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            return entity;
        }
        //Hashmap para pasar customerUserId a myBatis
        HashMap<String, Integer> options = new HashMap<>();
        options.put("idClient", idClient);
        try {
            this.sqlSession.update("deleteLogicClient", options);

            log.info("[CLIENT SERVICE] Client deleted Successfully! ");

            response.addProperty("status", "success");
            response.addProperty("message", "Client deleted successfully");
        }catch (Exception e){
            ResponseEntity entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            return entity;

        }
        return GeneralUtils.createOkResponse(response.toString());
    }
}
