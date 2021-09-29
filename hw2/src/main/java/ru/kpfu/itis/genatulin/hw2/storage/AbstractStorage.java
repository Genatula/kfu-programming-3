package ru.kpfu.itis.genatulin.hw2.storage;

import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractStorage<T> {
    public abstract ArrayList<T> getAll() throws IOException;
    public abstract void save(T obj) throws UserAlreadyExistsException, IOException;
}
