package com.canse.discord.service;
import com.canse.discord.dto.UserDto;
import com.canse.discord.exceptions.ObjectValidationException;
import com.canse.discord.models.Role;
import com.canse.discord.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void shoudSaveUserWithSuccess(){                                                               // Success All
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("test1@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Integer savedUser = userService.save(excpectedUser);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.intValue());
    }

    @Test
    public void shoudFindUserByIdWithSuccess(){                                                    // Success Find By Id
        UserDto excpectedUser = UserDto.builder()
                .id(3)
                .active(true)
                .email("test4@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        UserDto savedUser = userService.findById(3);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertNotNull(savedUser.getLastname());
        Assertions.assertNotNull(savedUser.getFirstname());
        Assertions.assertNotNull(savedUser.getEmail());
        Assertions.assertNotNull(savedUser.getPassword());
        Assertions.assertNotNull(savedUser.getRoles());
        Assertions.assertNotNull(savedUser.isActive());
    }

    @Test
    public void shoudUpdateUserWithSuccess(){                                      // SUCCESS -> Save Update and re find
        UserDto excpectedUser = UserDto.builder()
                .id(22)
                .active(true)
                .email("test2@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Integer savedUser = userService.save(excpectedUser);
        UserDto userToUpdate = userService.findById(savedUser);

        userToUpdate.setLastname("modify");
        userToUpdate.setFirstname("MODIFY");
        userToUpdate.setPassword("PasswordMODIFY");
        userToUpdate.setEmail("test@modify.test");

        Integer ModifyId = userService.save(userToUpdate);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.intValue());

        UserDto userToUpdateVerify = userService.findById(ModifyId);

        Assertions.assertNotNull(userToUpdateVerify.getLastname());
        Assertions.assertNotNull(userToUpdateVerify.getFirstname());
        Assertions.assertNotNull(userToUpdateVerify.getEmail());
        Assertions.assertNotNull(userToUpdateVerify.getPassword());
        Assertions.assertNotNull(userToUpdateVerify.getRoles());
        Assertions.assertNotNull(userToUpdateVerify.isActive());

        Assertions.assertEquals(22,userToUpdateVerify.getId());
        Assertions.assertEquals("modify",userToUpdateVerify.getLastname());
        Assertions.assertEquals("modify",userToUpdateVerify.getFirstname()); // lower case sur les dto
        Assertions.assertEquals("test@modify.test",userToUpdateVerify.getEmail());
    }

    @Test
    public void shoudDeleteUserWithSuccess(){                                                       // SUCCESS -> Delete
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("test@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Integer savedUser = userService.save(excpectedUser);
        UserDto userToDelete = userService.findById(savedUser);
        userService.delete(userToDelete.getId());

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class,() -> userService.findById(savedUser));
        Assertions.assertEquals("Aucun utilisateur trouvé avec cet Id : 1", exception.getMessage());
    }

    @Test
    public void OnSaveEmailAtDefaultShoudThrowObjectValidationException(){                                 // FAIL -> Mail @
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("testtest.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Assertions.assertThrows(ObjectValidationException.class, () -> userService.save(excpectedUser));
    }

    @Test
    public void OnSaveEmailSpaceInDefaultShoudThrowObjectValidationException(){                 // FAIL -> Mail Space In
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("test @test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Assertions.assertThrows(ObjectValidationException.class, () -> userService.save(excpectedUser));
    }

    @Test
    public void OnSaveEmailUserWithBorderSpaceDefaultShoudThrowObjectValidationException(){ // FAIL -> Mail Border Space
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email(" test@test.test ")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();
        Assertions.assertThrows(ObjectValidationException.class, () -> userService.save(excpectedUser));
    }

    @Test
    public void OnSaveUserWithToShirtPasswordDefaultShoudThrowObjectValidationException(){      // FAIL -> Pass To Shirt
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("test@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtes")
                .build();
        Assertions.assertThrows(ObjectValidationException.class, () -> userService.save(excpectedUser));
    }

    @Test
    public void OnSaveUserWithToLongPasswordDefaultShoudThrowObjectValidationException(){        // FAIL -> Pass To Long
        UserDto excpectedUser = UserDto.builder()
                .id(1)
                .active(true)
                .email("test@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttesttesttestt")
                .build();
        Assertions.assertThrows(ObjectValidationException.class, () -> userService.save(excpectedUser));
    }

    @Test
    public void ShoudThrowEntityNotFound(){                                                      // FAIL -> Id Not Found
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class,() -> userService.findById(0));
        Assertions.assertEquals("Aucun utilisateur trouvé avec cet Id : 0", exception.getMessage());
    }

    @Test
    public void OnSaveTwoUserWithSameEmailShoudThrowUniqConstraintException(){              // FAIL -> Unic constraint @                                                    // Success All
        UserDto excpectedUser1 = UserDto.builder()
                .id(1)
                .active(true)
                .email("test@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TEST")
                .lastname("test")
                .password("testtesttest")
                .build();

        userService.save(excpectedUser1);

        UserDto excpectedUser2 = UserDto.builder()
                .id(2)
                .active(true)
                .email("test@test.test")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .firstname("TESToo")
                .lastname("testoo")
                .password("testtesttestoo")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userService.save(excpectedUser2));

    }



}
