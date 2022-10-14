package com.revature.utils;

import com.revature.models.Ticket;

import java.util.List;

public interface CRUDDaoInterface <T>{

    //DAO - Data Access Object
    // a pattern that provides an abstract interface to some type of database or other persistence mechanism

    // were are returning an int because we are returning the primary key of this newly added row
    int create(T t);

    List<Ticket> getAll(int id);

    List<Ticket> getAll();
    List<Ticket> getAllPending();

    T getById(int id);

    T login(T t);

    boolean delete(T t);
}
