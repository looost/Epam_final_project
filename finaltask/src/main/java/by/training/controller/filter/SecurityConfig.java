package by.training.controller.filter;

import by.training.controller.command.CommandName;
import by.training.model.RoleEnum;

import java.util.*;

class SecurityConfig {
    private static final Map<RoleEnum, List<CommandName>> mapConfig = new EnumMap<>(RoleEnum.class);
    private static final Set<CommandName> securityPage = new HashSet<>();

    private SecurityConfig() {
    }


    static {
        securityPage.add(CommandName.SAVE_COMMENT);
        securityPage.add(CommandName.ADMIN_USER);
        securityPage.add(CommandName.MY_SERIAL);
        securityPage.add(CommandName.LIKED);
        securityPage.add(CommandName.WATCH_SERIAL);
        securityPage.add(CommandName.STOP_WATCH_SERIAL);
        securityPage.add(CommandName.LIKE);
        securityPage.add(CommandName.DISLIKE);
    }

    static boolean isSecurityPage(CommandName commandName) {
        return securityPage.contains(commandName);
    }

    static {
        List<CommandName> urlAdminPattern = new ArrayList<>();
        urlAdminPattern.add(CommandName.SAVE_COMMENT);
        mapConfig.put(RoleEnum.ADMIN, urlAdminPattern);

        List<CommandName> urlModerPattern = new ArrayList<>();
        urlModerPattern.add(CommandName.ADMIN_USER);
        mapConfig.put(RoleEnum.MODER, urlModerPattern);

        List<CommandName> urlUserPattern = new ArrayList<>();
        urlUserPattern.add(CommandName.ADMIN_USER);
        mapConfig.put(RoleEnum.USER, urlUserPattern);
    }

    static boolean hasPermission(CommandName command, RoleEnum role) {
        List<CommandName> urlPatterns = mapConfig.get(role);
        return urlPatterns != null && !urlPatterns.contains(command);
    }
}
