package com.example.controllers.interfaces;

public abstract class CallbackableController implements Controller {
    protected Controller callbackController;

    public void withCallback(Controller callbackController) {
        this.callbackController = callbackController;
    }
}
