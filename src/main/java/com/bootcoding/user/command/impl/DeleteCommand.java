package com.bootcoding.user.command.impl;

import com.bootcoding.user.command.Command;
import com.bootcoding.user.command.validator.CommandValidator;
import com.bootcoding.user.model.Result;
import com.bootcoding.user.model.User;
import com.bootcoding.user.store.InMemoryStore;

public class DeleteCommand implements Command, CommandValidator {

    //   delete -i  dhdshds773737
    @Override
    public Result execute(String[] attributes) {
        if (validate(attributes)) {
            for (User user : InMemoryStore.users) {
                if (user.getId().equals(attributes[2])) {
                    deleteUser(attributes);
                }
            }
        }
        // write logic for delete user
        return null;
    }

    private void deleteUser(String[] attributes) {
        for (User user : InMemoryStore.users) {
            String id = user.getId();
        }

    }

    @Override
    public boolean validate(String[] attributes) {
        return attributes.length == 2 && attributes.equals("delete");
    }
}
