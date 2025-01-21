package com.example.a7.view.commands;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.RepoException;
import com.example.a7.exception.StatementException;

public abstract class Command {
    private String key;
    private String description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws StatementException, ADTException, RepoException, ExpressionException;

    public String getKey() {
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }
}
