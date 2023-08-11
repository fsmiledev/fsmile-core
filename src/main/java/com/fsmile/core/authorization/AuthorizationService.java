package com.fsmile.core.authorization;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.core.authorization
 * Author revouna
 * Date 07/08/2023
 */
public interface AuthorizationService {
    void addGroup(Group group);
    void addRole(Role role);
    void addUserToGroup(String userId, String groupId);
    void removeUserToGroup(String userId, String groupId);
    List<Group> getGroupByUser(String userId);
    List<Group> getAllGroup();
    List<Client> getAllClient();
    Client getClientById(String clientId);
    String saveClient(Client client);
    void deleteClient(String clientId);



}
