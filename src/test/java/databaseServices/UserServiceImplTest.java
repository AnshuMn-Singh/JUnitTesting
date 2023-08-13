package databaseServices;

import databaseServices.io.UsersDatabase;
import databaseServices.io.UsersDatabaseMapImpl;
import databaseServices.service.UserService;
import databaseServices.service.UserServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {
    UsersDatabase usersDatabase;
    UserService userService;
    String createdUserId;


    @BeforeAll
    void setup() { // Create & initialize database
        usersDatabase = new UsersDatabaseMapImpl();
        usersDatabase.init();
        userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {// Close connection(Delete database)
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "A");
        user.put("lastName", "B");

        createdUserId = userService.createUser(user);

        assertNotNull(createdUserId, "User id should not be null");
        }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {
        Map<String, String> newUserDetails = new HashMap<>();
        newUserDetails.put("firstName", "C");
        newUserDetails.put("lastName", "B");

        Map updateUserDetails = userService.updateUser(createdUserId, newUserDetails);

        assertEquals(newUserDetails.get("firstName"), updateUserDetails.get("firstName"), "v");
        assertEquals(newUserDetails.get("firstName"), updateUserDetails.get("firstName"));
        assertEquals(newUserDetails.get("lastName"), updateUserDetails.get("lastName"));
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {
        Map userDetails = userService.getUserDetails(createdUserId);
        assertNotNull(userDetails);
        assertEquals(createdUserId, userDetails.get("userId"));
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {
        userService.deleteUser(createdUserId);
        assertNull(userService.getUserDetails(createdUserId), () -> "is null");
    }
}
