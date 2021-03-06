package com.theorev.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TemplateController implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean state;
    String us;
    FacesContext context;

    public void verifySession() {
        if (!isState()) {
            try {
                context = FacesContext.getCurrentInstance();
                us = (String) context.getExternalContext().getSessionMap().get("user");
                setState(true);

                if (us == null) {
                    context.getExternalContext().redirect("./../index.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            us = (String) context.getExternalContext().getSessionMap().get("user");
        }
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
