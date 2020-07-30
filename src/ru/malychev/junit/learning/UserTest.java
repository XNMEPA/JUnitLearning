package ru.malychev.junit.learning;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTest {
    private User user, user1, user2;
    private User userNotAdd, userNotAdd1;

    @Before
    public void setUP() {
        user = new User("Евгений", 35, Sex.MALE);
        user1 = new User("Марина", 34, Sex.FEMALE);
        user2 = new User("Алина", 7, Sex.FEMALE);

        userNotAdd = new User("", 0, null);
        userNotAdd1 = new User(null, 0, null);
    }

    @After
    public void downShift() {
        User.delUsers();
    }

    @Test
    public void newUser_EmptyName() {
        for(User user : User.getAllUsers())
            if (user.getName().isEmpty() || user.getName() == null)
                Assert.fail("Был создан безымянный пользователь.");
   }

   @Test
   public void newUser_Age0() {
       for(User user : User.getAllUsers())
           if (user.getAge() <= 0)
               Assert.fail("Был создан пользователь с недопустимым возрастом.");

   }

   @Test
   public void newUser_WithoutSex() {
       for(User user : User.getAllUsers())
           if (user.getSex() == null)
               Assert.fail("Был создан бесполый пользователь.");

   }

    @Test
    public void getAllUsers() {
        List<User> expected = User.getAllUsers();
        Assert.assertNotNull(expected);

        expected = User.getAllUsers();

        List<User> actual = new ArrayList<>();
        actual.add(user);
        actual.add(user1);
        actual.add(user2);
        Assert.assertEquals(expected, actual);
        expected = null;


        expected = User.getAllUsers(Sex.MALE);
        Assert.assertNotNull(expected);
        actual.clear();
        actual.add(user);
        Assert.assertEquals(expected, actual);
        expected = null;


        expected = User.getAllUsers(Sex.FEMALE);
        Assert.assertNotNull(expected);
        actual = Arrays.asList(user1, user2);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getHowManyUsers() {
        int manyUsers = User.getHowManyUsers();
        int actual = 3;
        Assert.assertEquals(manyUsers, actual);

        manyUsers = User.getHowManyUsers(Sex.MALE);
        actual = 1;
        Assert.assertEquals(manyUsers, actual);

        manyUsers = User.getHowManyUsers(Sex.FEMALE);
        actual = 2;
        Assert.assertEquals(manyUsers, actual);

    }

    @Test
    public void getAllAgeUsers() {
        int howAge = User.getAllAgeUsers();
        int actual = 35 + 34 + 7;
        Assert.assertEquals(howAge, actual);

        howAge = User.getAllAgeUsers(Sex.MALE);
        actual = 35;
        Assert.assertEquals(howAge, actual);

        howAge = User.getAllAgeUsers(Sex.FEMALE);
        actual = 34 + 7;
        Assert.assertEquals(howAge, actual);
    }

    @Test
    public void getAverageAgeOfAllUsers() {
        int averageAge = User.getAverageAgeOfAllUsers();
        int actual = (35 + 34 + 7) / 3;
        Assert.assertEquals(averageAge, actual);

        averageAge = User.getAverageAgeOfAllUsers(Sex.MALE);
        actual = 35;
        Assert.assertEquals(averageAge, actual);

        averageAge = User.getAverageAgeOfAllUsers(Sex.FEMALE);
        actual = (34 + 7) / 2;
        Assert.assertEquals(averageAge, actual);
    }
}