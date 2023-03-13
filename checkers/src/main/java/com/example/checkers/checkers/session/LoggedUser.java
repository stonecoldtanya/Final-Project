package com.example.checkers.checkers.session;

import com.example.checkers.checkers.models.entities.Contestant;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoggedUser {
    private long id;

    private boolean logged;

    public void login(Contestant user) {
        this.id = user.getId();
        this.logged = true;
    }

    public void logout() {
        this.id = 0;
        this.logged = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
