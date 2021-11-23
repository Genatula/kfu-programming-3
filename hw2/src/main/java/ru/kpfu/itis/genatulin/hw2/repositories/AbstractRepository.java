package ru.kpfu.itis.genatulin.hw2.repositories;

import ru.kpfu.itis.genatulin.hw2.exceptions.UserAlreadyExistsException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractRepository<T> {
    public abstract ArrayList<T> getAllEntries() throws IOException, SQLException;
    public abstract void saveEntry(T obj) throws UserAlreadyExistsException, SQLException;
    public abstract T getEntry(String... keys) throws SQLException;
}
