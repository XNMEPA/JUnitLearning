package ru.malychev.junit.learning;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {
    private int id;
    private String name;
    private int age;
    private Sex sex;

    private static Map<Integer, User> allUsers = new HashMap<>();
    private static int countId = 0;

    public static void main(String[] args) {
	// write your code here
        new User("Евгений", 35, Sex.MALE);
        new User("Марина", 34, Sex.FEMALE);
        new User("Алина", 7, Sex.FEMALE);
        new User("", 0, null);
        new User(null, 0, null);



        System.out.println("Все пользователи:");
        User.getAllUsers().forEach(System.out::println);
        System.out.println("Все пользователи: MALE");
        User.getAllUsers(Sex.MALE).forEach(System.out::println);
        System.out.println("Все пользователи: FEMALE");
        User.getAllUsers(Sex.FEMALE).forEach(System.out::println);
        System.out.println("================================================");
        System.out.println("       всех пользователей: " + User.getHowManyUsers());
        System.out.println("  всех пользователей MALE: " + User.getHowManyUsers(Sex.MALE));
        System.out.println("всех пользователей FEMALE: " + User.getHowManyUsers(Sex.FEMALE));
        System.out.println("================================================");
        System.out.println("       общий возраст всех пользователей: " + User.getAllAgeUsers());
        System.out.println("  общий возраст всех пользователей MALE: " + User.getAllAgeUsers(Sex.MALE));
        System.out.println("общий возраст всех пользователей FEMALE: " + User.getAllAgeUsers(Sex.FEMALE));
        System.out.println("================================================");
        System.out.println("       средний возраст всех пользователей: " + User.getAverageAgeOfAllUsers());
        System.out.println("  средний возраст всех пользователей MALE: " + User.getAverageAgeOfAllUsers(Sex.MALE));
        System.out.println("средний возраст всех пользователей FEMALE: " + User.getAverageAgeOfAllUsers(Sex.FEMALE));
        System.out.println("================================================");
    }

    public User(String name, int age, Sex sex) {
        if (name != null && !name.isEmpty() && age > 0 && sex != null) {
            this.name = name;
            this.age = age;
            this.sex = sex;

            if (!hasUser()) {
                id = ++countId;
                allUsers.put(id, this);
            }
        }
    }

    public static void delUsers() {
        allUsers.clear();
        countId = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    public static List<User> getAllUsers(Sex sex) {
        return allUsers
                .values()
                .stream()
                .filter(user -> user.getSex() == sex)
                .collect(Collectors.toList());
    }

    public static int getHowManyUsers() {
        return allUsers.size();
    }

    public static int getHowManyUsers(Sex sex) {
        return getAllUsers(sex).size();
    }

    public static int getAllAgeUsers() {
        return allUsers
                .values()
                .stream()
                .flatMapToInt(user -> IntStream.of(user.getAge()))
                .sum();
    }

    public static int getAllAgeUsers(Sex sex) {
        return getAllUsers(sex)
                .stream()
                .flatMapToInt(user -> IntStream.of(user.getAge()))
                .sum();
    }

    public static int getAverageAgeOfAllUsers() {
        return getAllAgeUsers() / getHowManyUsers();
    }

    public static int getAverageAgeOfAllUsers(Sex sex) {
        return getAllAgeUsers(sex) / getHowManyUsers(sex);
    }

    private boolean hasUser() {
        for (User user : allUsers.values())
            if(user.equals(this) && user.hashCode() == this.hashCode()) return true;
        return false;
    }

    @Override
    public String toString() {
        return  "User {" +
                "id = " + id +
                ", name = '" + name + "'" +
                ", age = " + age +
                ", sex = " + sex +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.getName()) &&
               age == user.getAge() &&
               sex == user.getSex();
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }
}
