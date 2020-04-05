package by.training.controller.filter;

import by.training.controller.command.CommandName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityUtils {
    private static final Set<CommandName> securityPage = new HashSet<>();

    private SecurityUtils() {
    }

    static {
        securityPage.add(CommandName.PROFILE);
        securityPage.add(CommandName.PROFILEPOST);
        securityPage.add(CommandName.ADD_COMMENT);
        securityPage.add(CommandName.ADD_GENRE);
    }

    static boolean isSecurityPage(CommandName commandName) {
        return securityPage.contains(commandName);
    }

    static boolean hasPermission(CommandName command, RoleEnum role) {
        List<CommandName> urlPatterns = SecurityConfig.getUrlPatternForRole(role);
        return urlPatterns != null && urlPatterns.contains(command);
    }
}
