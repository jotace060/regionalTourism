package com.regionalTourism.controller;

import com.regionalTourism.dto.Client;
import com.regionalTourism.services.ClientService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@CommonsLog
//@CrossOrigin
//@RequestMapping(produces = "application/json")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private final SqlSession sqlSession;

    public ClientController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @PostMapping("/registerClient")
    @ResponseBody
    public ResponseEntity registerNewCustomer(@RequestBody Client client) {
        System.out.println("TEST");
        return clientService.createClient(client);
    }
    @RequestMapping(value = "/deleteLogicClient/{idClient}", method = RequestMethod.POST, produces={"application/json"})
     public ResponseEntity deleteLogicClient(@PathVariable Integer idClient) {

        return clientService.deleteLogicClient(idClient);

    }

    @PostMapping("/updateClient")
    @ResponseBody
    public ResponseEntity updateClient(@RequestBody Client client) {
        System.out.println("TESTUPDATE");
        return clientService.updateClient(client);

    }

    @GetMapping("/getAllClients")
    @ResponseBody
    public List<Client> getAllClients() {
        return this.sqlSession.selectList("getAllClients",null);
    }



    @GetMapping("/getClientById")
    @ResponseBody
    public Client getAllClients(Integer idClient) {
        return this.sqlSession.selectOne("getClientById",idClient);
    }

}


