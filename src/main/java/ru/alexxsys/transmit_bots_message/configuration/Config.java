package ru.alexxsys.transmit_bots_message.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Service
//@Scope(WebApplicationContext.)
@Configuration
public class Config {

    private String pathRemoteSystem;
    private String login;
    private String password;
    private boolean enableAutoSend;

    public Config() {
    }
    public Config(String pathRemoteSystem, String login, String password, boolean enableAutoSend) {
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

    @Bean()
    public Config getBean(){
        return new Config("localhost", "", "", true);
    }
}
