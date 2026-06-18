package com.enterpriseai.config.initializer;

import com.enterpriseai.user.entity.Role;
import com.enterpriseai.user.entity.RoleName;
import com.enterpriseai.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole(RoleName.ADMIN);
        createRole(RoleName.USER);

    }

    private void createRole(RoleName roleName) {

        if (roleRepository.findByName(roleName).isEmpty()) {

            roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );

        }

    }

}