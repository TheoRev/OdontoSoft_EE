package com.theorev.controller;

import com.theorev.dao.UsersDao;
import com.theorev.model.Users;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsersController implements Serializable {

    private Users user;

    @PostConstruct
    public void init() {
        user = new Users();
    }

    public String login() {
        FacesMessage message = null;
        String url = null;

        UsersDao dao = new UsersDao();

        try {
            Users u = dao.findById(user.getUsername(), user.getPassword());

            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user.getUsername());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ACCESO CONCEDIDO",
                        "Bienvenido " + user.getUsername().toUpperCase());
                url = "views/patient?faces-redirect=true";
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR AL VALIDAR",
                        "Username o Password incorrectos");
                url = "index";
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR AL VALIDAR",
                    "Username o Password incorrectos");
            url = "index";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        return url;
    }

    public void closeSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
