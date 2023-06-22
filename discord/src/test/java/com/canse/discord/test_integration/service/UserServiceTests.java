package com.canse.discord.test_integration.service;
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
    public void shoudSaveUserWithSuccess(){
        UserDto excpectedUser = UserDto.builder()
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
    public void shoudFindUserById_Success(){
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
    }

    @Test
    public void shoudUpdateUser_Success(){
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
        Assertions.assertEquals("Modify",userToUpdateVerify.getLastname());
        Assertions.assertEquals("Modify",userToUpdateVerify.getFirstname());
        Assertions.assertEquals("Test@modify.test",userToUpdateVerify.getEmail());
    }

    @Test
    public void shoudDeleteUser_Success(){
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
    public void OnSaveEmailAtDefault_ThrowObjectValidationException(){
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
    public void OnSaveEmailSpaceInDefault_ThrowObjectValidationException(){
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
    public void OnSaveEmailUserWithBorderSpace_ThrowObjectValidationException(){
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
    public void OnSaveUserWithToShirtPassword_ThrowObjectValidationException(){
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
    public void OnSaveUserWithToLongPassword_ThrowObjectValidationException(){
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
    public void findIdNonExist_EntityNotFound(){
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class,() -> userService.findById(0));
        Assertions.assertEquals("Aucun utilisateur trouvé avec cet Id : 0", exception.getMessage());
    }

    @Test
    public void OnSaveTwoUserWithSameEmail_ThrowUniqConstraintException(){
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
