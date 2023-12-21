package com.bootcoding.user.command.impl;

import com.bootcoding.user.command.Command;
import com.bootcoding.user.command.validator.CommandValidator;
import com.bootcoding.user.model.Result;
import com.bootcoding.user.model.User;
import com.bootcoding.user.store.InMemoryStore;

import java.util.ArrayList;
import java.util.List;

public class ReadCommand implements Command, CommandValidator {
    List<User> list = new ArrayList<>();

    @Override
    public Result execute(String[] attributes) throws Exception {
//            read -n John
//            read -i someUserId
//            read --all
        String attributeName = attributes[1];
        switch (attributeName) {
            case "-n":
                return getUserByName(attributes);
            case "--id":
                return getUserById(attributes);
            case "--all":
                return getAllUser(attributes);
            default:
                throw new Exception("please write correct attribute for finding with name:" + "read -n yourName" + "for finding with name: " + "read --id UserId" + "to get all user: " + "--all ");
        }
    }


    private Result getUserById(String[] attribute) throws Exception {
        if (validate(attribute)) {
            for (User userDetail : InMemoryStore.users) {
                if (userDetail.getId().equals(attribute[2])) {
                    list.add(userDetail);
                    return Result.builder().users(list).message("success").build();
                }
            }
        }
        return Result.builder().users(null).message("unable to find user with this id").build();
    }

    private Result getAllUser(String[] attribute) {
        if (attribute.length == 2 && attribute[0].equals("read")) {
            List<User> userList = InMemoryStore.users;
            return Result.builder().users(userList).message("Success").build();
        }
        return Result.builder().users(null).message("something went wrong").build();
    }


    private Result getUserByName(String[] attribute) throws Exception {
        if (validate(attribute)) {
            List<User> users = InMemoryStore.users;
            for (User userDetail : users) {
                if (userDetail.getName().equals(attribute[2])) {
                    list.add(userDetail);
                    return Result.builder().users(list).message("success").build();
                }
            }
            return Result.builder().users(list).message("success").build();
        }
        return Result.builder().users(null).message("unable to find user with this name").build();
    }

    @Override
    public boolean validate(String[] attributes) throws Exception {
        if (attributes.length != 3) {
            throw new Exception("Please provide name : " + "For ex: \nread -n \"Ramesh\" ");
        }
        if (!attributes[0].equals("read")) {
            throw new Exception("Action is not read!");
        }
        return true;
    }
}
