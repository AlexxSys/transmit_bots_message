package ru.alexxsys.transmit_bots_message.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

//@Service
//@Scope(WebApplicationContext.)
@Configuration
@Scope(value = "singleton")
public class ConfigTransferSystem {

    private String pathRemoteSystem;
    private String login;
    private String password;
    private boolean enableAutoSend;

    public ConfigTransferSystem() {
    }
    public ConfigTransferSystem(String pathRemoteSystem, String login, String password, boolean enableAutoSend) {
        this.pathRemoteSystem = pathRemoteSystem;
        this.login = login;
        this.password = password;
        this.enableAutoSend = enableAutoSend;
    }

    public String getPathRemoteSystem() {
        return pathRemoteSystem;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public boolean isEnableAutoSend() {
        return enableAutoSend;
    }
    public void setPathRemoteSystem(String pathRemoteSystem) {
        this.pathRemoteSystem = pathRemoteSystem;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnableAutoSend(boolean enableAutoSend) {
        this.enableAutoSend = enableAutoSend;
    }
}
