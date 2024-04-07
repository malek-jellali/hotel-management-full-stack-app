package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.model.Role;
import com.backend.hotelmanagement.model.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);
    void deleteRole(Long id);
    Role findRoleByName(String name);

    User removeUserFromRole(Long userId , Long roleId );

    User assignRoleToUser(Long userId , Long roleId);

    Role removeAllUsersFromRole(Long roleId);
    Role findByName(String name);

}
