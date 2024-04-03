package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            /* Create PERMISSIONS */
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /* Create ROLES */
            RoleEntity adminRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity userRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity invitedRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.INIVITED)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity developerRole = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

            /* Create USERS */
            UserEntity userNico = UserEntity.builder()
                    .username("nico")
                    .password("$2a$10$TkOOgXRzsIU9I4GfZ2PGjeegvxv86PgcMdp5cMp3rwtwS6lA5aS52")
                    .isEnable(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(adminRole))
                    .build();

            UserEntity userDaniel = UserEntity.builder()
                    .username("daniel")
                    .password("$2a$10$TkOOgXRzsIU9I4GfZ2PGjeegvxv86PgcMdp5cMp3rwtwS6lA5aS52")
                    .isEnable(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(userRole))
                    .build();

            UserEntity userAndrea = UserEntity.builder()
                    .username("andrea")
                    .password("$2a$10$TkOOgXRzsIU9I4GfZ2PGjeegvxv86PgcMdp5cMp3rwtwS6lA5aS52")
                    .isEnable(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(invitedRole))
                    .build();

            UserEntity userAnyi = UserEntity.builder()
                    .username("anyi")
                    .password("$2a$10$TkOOgXRzsIU9I4GfZ2PGjeegvxv86PgcMdp5cMp3rwtwS6lA5aS52")
                    .isEnable(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(developerRole))
                    .build();

            userRepository.saveAll(List.of(userNico,userDaniel,userAndrea,userAnyi));
        };
    }
}
