package ru.kpfu.itis.genatulin.hw2.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.kpfu.itis.genatulin.hw2.entities.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UsersStorage extends AbstractStorage<User> {

    private final Gson gson;
    private final Type arrayListType;
    private final String filename;

    public UsersStorage(String filename) {
        this.filename = filename;
        this.gson = new Gson();
        this.arrayListType = new TypeToken<List<User>>(){}.getType();
    }

    @Override
    public ArrayList<User> getAll() throws IOException {
        FileReader reader = new FileReader(filename);
        ArrayList<User> users = gson.fromJson(reader, arrayListType);
        reader.close();
        return users;
    }

    @Override
    public void save(User user) throws UserAlreadyExistsException, IOException {
        if (this.getAll() == null ||this.getAll().size() == 0) {
            HashSet<User> users = new HashSet<>();
            users.add(user);
            FileWriter writer = new FileWriter(filename);
            gson.toJson(new ArrayList<>(users), arrayListType, writer);
            writer.flush();
            writer.close();
        }
        else {
            HashSet<User> users = new HashSet<>(this.getAll());
            if (users.contains(user)) {
                throw new UserAlreadyExistsException();
            }
            users.add(user);
            FileWriter writer = new FileWriter(filename);
            gson.toJson(new ArrayList<>(users), arrayListType, writer);
            writer.flush();
            writer.close();
        }
    }
}
