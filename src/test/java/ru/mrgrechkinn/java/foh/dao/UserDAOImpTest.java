package ru.mrgrechkinn.java.foh.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import ru.mrgrechkinn.java.foh.model.User;

/**
 * Tests for {@link UserDAOImpl}
 * 
 * @author Eugene Rudenko
 */
@RunWith(PowerMockRunner.class)
//@PrepareForTest({UserDAOImpl.class})
public class UserDAOImpTest {

    private UserDAO userDao;
    private File testFile;

    @Before
    public void setUpBeforeClass() throws NoSuchFieldException, SecurityException, Exception {
//        PowerMockito.suppress(PowerMockito.constructor(UserDAOImpl.class));
        testFile = File.createTempFile("users_", "_UserDAOImpl.txt");
//        userDao = new UserDAOImpl();
        Whitebox.setInternalState(userDao, "file", testFile);
    }

    /*@AfterClass
    public static void tearDownAfterClass() {
        userDao = null;
    }*/

    @Test
    public void testSave() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        User user = new User();
        user.setLogin("test");
        user.setFullName("Ivan T");
        user.setPassword("&fhjdksf");
        userDao.save(user);

//        Assert.assertEquals(String.valueOf(user.getId()), reader.readLine());
        Assert.assertEquals(String.valueOf(user.getLogin()), reader.readLine());
        Assert.assertEquals(String.valueOf(user.getPassword()), reader.readLine());
        Assert.assertEquals(String.valueOf(user.getFullName()), reader.readLine());

        try {
            reader.close();
        } catch (IOException ioe) {
        }
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setLogin("user1");
        user.setFullName("User1FullName");
        user.setPassword("user1pass");
        userDao.save(user);

        User user2 = new User();
        user2.setLogin("user2");
        user2.setFullName("User2FullName");
        user2.setPassword("user2pass");
        userDao.save(user2);

        userDao.delete(user2);

        List<User> allUsers = userDao.getAllUsers();
        Assert.assertEquals(1, allUsers.size());

        User userFromDAO = allUsers.get(0);
//        Assert.assertEquals(user.getId(), userFromDAO.getId());
        Assert.assertEquals(user.getLogin(), userFromDAO.getLogin());
        Assert.assertEquals(user.getFullName(), userFromDAO.getFullName());
        Assert.assertEquals(user.getPassword(), userFromDAO.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setLogin("user1");
        user.setFullName("User1FullName");
        user.setPassword("user1pass");
        userDao.save(user);

        user = new User();
        user.setLogin("user2");
        user.setFullName("User2FullName");
        user.setPassword("user2pass");
        userDao.save(user);

        user = new User();
        user.setLogin("user3");
        user.setFullName("User3FullName");
        user.setPassword("user3pass");
        userDao.save(user);

        Assert.assertEquals(3, userDao.getAllUsers().size());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setLogin("user1");
        user.setFullName("User1FullName");
        user.setPassword("user1pass");
        userDao.save(user);

        User user2 = new User();
        user2.setLogin("user2");
        user2.setFullName("User2FullName");
        user2.setPassword("user2pass");
        userDao.save(user2);

//        User userFromDAO = userDao.getUserById(user2.getId());
//        Assert.assertEquals(user2.getId(), userFromDAO.getId());
//        Assert.assertEquals(user2.getLogin(), userFromDAO.getLogin());
//        Assert.assertEquals(user2.getFullName(), userFromDAO.getFullName());
//        Assert.assertEquals(user2.getPassword(), userFromDAO.getPassword());
    }

}
