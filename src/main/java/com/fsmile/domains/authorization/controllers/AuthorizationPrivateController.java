package com.fsmile.domains.authorization.controllers;

import com.fsmile.core.authorization.AuthorizationService;
import com.fsmile.core.authorization.Client;
import com.fsmile.core.authorization.Group;
import com.fsmile.core.authorization.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.authorization.controllers
 * Author revouna
 * Date 11/08/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authorization/private")
public class AuthorizationPrivateController {

    private final AuthorizationService authorizationService;

    @PostMapping("addGroup")
    public ResponseEntity<?> addGroup(@RequestBody Group group) {
        authorizationService.addGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("addRole")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        authorizationService.addRole(role);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("addUserToGroup/{groupId}")
    public ResponseEntity<?> addUserToGroup(@RequestBody String userId, @PathVariable String groupId) {
        authorizationService.addUserToGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("removeUserToGroup/{groupId}")
    public ResponseEntity<?> removeUserToGroup(@RequestBody String userId, @PathVariable String groupId) {
        authorizationService.removeUserToGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getGroupByUser/{userId}")
    public ResponseEntity<?> getGroupByUser(@PathVariable String userId) {
        return new ResponseEntity<>(authorizationService.getGroupByUser(userId), HttpStatus.OK);
    }

    @GetMapping("getAllGroup")
    public ResponseEntity<?> getAllGroup() {
        return new ResponseEntity<>(authorizationService.getAllGroup(), HttpStatus.OK);
    }

    @GetMapping("getAllClient")
    public ResponseEntity<?> getAllClient() {
        return new ResponseEntity<>(authorizationService.getAllClient(), HttpStatus.OK);
    }

    @GetMapping("getClientById/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable String clientId) {
        return new ResponseEntity<>(authorizationService.getClientById(clientId), HttpStatus.OK);
    }

    @PostMapping("saveClient")
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        return new ResponseEntity<>(authorizationService.saveClient(client), HttpStatus.CREATED);
    }

    @DeleteMapping("deleteClient/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable String clientId) {
        authorizationService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
