package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.exceptions.RoleAlreadyExistException;
import com.backend.hotelmanagement.exceptions.UserAlreadyExistsException;
import com.backend.hotelmanagement.model.Role;
import com.backend.hotelmanagement.model.User;
import com.backend.hotelmanagement.repository.RoleRepository;
import com.backend.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


    @Service
    @RequiredArgsConstructor
    public class RoleServiceImpl implements RoleService {
        private final RoleRepository roleRepository;
        private final UserRepository userRepository;

        @Override
        public List<Role> getRoles() {
            return roleRepository.findAll();
        }

        @Override
        public Role createRole(Role theRole) {
            String roleName = "ROLE_"+theRole.getName().toUpperCase();
            Role role = new Role(roleName);
            if (roleRepository.existsByName(roleName))
                throw new RoleAlreadyExistException(theRole.getName() + " role already exists");
            return roleRepository.save(role);
        }

        @Override
        public void deleteRole(Long roleId) {
            this.removeAllUsersFromRole(roleId);
            roleRepository.deleteById(roleId);
        }

        @Override
        public Role findRoleByName(String name) {
            return null;
        }

        @Override
        public Role findByName(String name) {
            return roleRepository.findByName(name).get();
        }

        @Override
        public User removeUserFromRole(Long userId, Long roleId) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Role>  role = roleRepository.findById(roleId);
            if (role.isPresent() && role.get().getUsers().contains(user.get())){
                role.get().removeUserFromRole(user.get());
                roleRepository.save(role.get());
                return user.get();
            }
            throw new UsernameNotFoundException("User not found");
        }

        @Override
        public User assignRoleToUser(Long userId, Long roleId) {
            Optional<User> user = userRepository.findById(userId);
            Optional<Role>  role = roleRepository.findById(roleId);
            if (user.isPresent() && user.get().getRoles().contains(role.get())){
                throw new UserAlreadyExistsException(
                        user.get().getFirstName()+ " is already assigned to the" + role.get().getName()+ " role");
            }
            if (role.isPresent()){
                role.get().assignRoleToUser(user.get());
                roleRepository.save(role.get());
            }
            return user.get();
        }

        @Override
        public Role removeAllUsersFromRole(Long roleId) {
            Optional<Role> role = roleRepository.findById(roleId);
            role.ifPresent(Role::removeAllUsersFromRole);
            return roleRepository.save(role.get());
        }
}
