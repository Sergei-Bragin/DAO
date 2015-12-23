package dao;


import model.Model;

import java.util.List;

public interface ItemDao <T extends Model> {


    List<T> getAll();

    T getById(int id);

    void add(T model);

    void update(T model);

    void del(T model);
}
