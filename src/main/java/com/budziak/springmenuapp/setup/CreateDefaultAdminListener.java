/*
package com.budziak.springmenuapp.setup;

import com.budziak.springmenuapp.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateDefaultAdminListener {
    private final UserService userService;

    public CreateDefaultAdminListener(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationEvent.class)
    public void createDefaultAdmin() {
        userService.createDefaultAdminIfNotExist();
    }
}
*/
