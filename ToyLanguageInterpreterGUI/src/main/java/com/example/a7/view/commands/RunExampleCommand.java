package com.example.a7.view.commands;

import com.example.a7.controller.Controller;
import com.example.a7.exception.*;

public class RunExampleCommand extends Command {
    private Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
    }

}
