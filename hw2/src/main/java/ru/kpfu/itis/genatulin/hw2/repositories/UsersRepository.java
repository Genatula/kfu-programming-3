package ru.kpfu.itis.genatulin.hw2.repositories;

import ru.kpfu.itis.genatulin.hw2.entities.User;
import ru.kpfu.itis.genatulin.hw2.exceptions.UserAlreadyExistsException;

import java.sql.*;
import java.util.ArrayList;

public class UsersRepository extends AbstractRepository<User> {

    private static UsersRepository usersRepository;
    private static Connection connection;

    private UsersRepository() throws SQLException, ClassNotFoundException {
        Class<Driver> driver = (Class<Driver>) Class.forName("org.postgresql.Driver");
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        connection = DriverManager.getConnection(dbURL, "postgres", "230764ugole");
    }

    public static UsersRepository getInstance() throws SQLException, ClassNotFoundException {
        if (usersRepository == null) {
            usersRepository = new UsersRepository();
        }
        return usersRepository;
    }

    @Override
    public ArrayList<User> getAllEntries() throws SQLException {
        Statement getAllStatement = connection.createStatement();
        ResultSet resultSet = getAllStatement.executeQuery("select * from users;");
        return this.toArrayOfUsers(resultSet);
    }

    @Override
    public void saveEntry(User user) throws UserAlreadyExistsException, SQLException {
        PreparedStatement findDuplicatesStatement = connection.prepareStatement("select from users where email = ?");
        findDuplicatesStatement.setString(1, user.getEmail());
        if (findDuplicatesStatement.executeQuery().next()) {
            throw new UserAlreadyExistsException();
        }
        else {
            PreparedStatement insertUserStatement = connection.prepareStatement("insert into users (email, password, username)" +
                    " values (?, ?, ?);");
            insertUserStatement.setString(1, user.getEmail());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, user.getUsername());
            insertUserStatement.execute();
        }
    }

    @Override
    public User getEntry(String... keys) throws SQLException {
        String email = keys[0];
        PreparedStatement findUserStatement = connection.prepareStatement("select * from users where email = ?");
        findUserStatement.setString(1, email);
        ArrayList<User> user = this.toArrayOfUsers(findUserStatement.executeQuery());
        if (user.size() == 0) {
            return null;
        }
        return user.get(0);
    }

    private ArrayList<User> toArrayOfUsers(ResultSet resultSet) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String email = (String) resultSet.getObject("email");
            String password = (String) resultSet.getObject("password");
            String username = (String) resultSet.getObject("username");
            users.add(new User(username, email, password));
        }
        return users;
    }
}
