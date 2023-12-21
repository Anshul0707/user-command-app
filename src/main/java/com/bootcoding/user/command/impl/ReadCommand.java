package com.bootcoding.user.command.impl;

import com.bootcoding.user.command.Command;
import com.bootcoding.user.command.validator.CommandValidator;
import com.bootcoding.user.model.Result;

public class ReadCommand implements Command, CommandValidator {
    @Override
    public Result execute(String[] attributes) {
        // write logic for read user
        return null;
    }

    @Override
    public boolean validate(String[] attributes) {
        return false;
    }
}
