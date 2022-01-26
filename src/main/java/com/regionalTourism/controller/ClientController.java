package com.regionalTourism.controller;

import com.regionalTourism.commons.GeneralUtils;
import com.regionalTourism.services.ClientService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CommonsLog
@CrossOrigin
@RequestMapping(produces = "application/json")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/registerClient")
    @ResponseBody
    public ResponseEntity registerNewCustomer(String documentClient,String firstName,String lastName, Integer age, String email) {

     return clientService.createClient(documentClient,firstName,lastName,age,email);

        }

    @PostMapping("/deleteLogicClient")
    @ResponseBody
    public ResponseEntity deleteLogicClient(Integer idClient) {

        return clientService.deleteLogicClient(idClient);

    }

    @PostMapping("/updateClient")
    @ResponseBody
    public ResponseEntity updateClient(Integer idClient, String documentClient,String firstName, String lastName, Integer age, String email,Integer status) {

        return clientService.updateClient(idClient,documentClient,firstName,lastName,age,email,status);

    }

    }


