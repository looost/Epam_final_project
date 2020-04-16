package by.training.controller.filter;

import by.training.controller.command.CommandName;

import java.util.*;

class SecurityConfig {
    private static final Map<RoleEnum, List<CommandName>> mapConfig = new EnumMap<>(RoleEnum.class);
    private static final Set<CommandName> securityPage = new HashSet<>();

    private SecurityConfig() {
    }


    static {
        //securityPage.add(CommandName.PROFILE);
        securityPage.add(CommandName.ADD_COMMENT);
        securityPage.add(CommandName.PROFILEPOST);
    }

    static boolean isSecurityPage(CommandName commandName) {
        return securityPage.contains(commandName);
    }
    static {
        List<CommandName> urlAdminPattern = new ArrayList<>();
        urlAdminPattern.add(CommandName.INDEX);
        urlAdminPattern.add(CommandName.PROFILE);
        urlAdminPattern.add(CommandName.PROFILEPOST);
        //urlAdminPattern.add(CommandName.ADD_GENRE);
        urlAdminPattern.add(CommandName.CHANGE_GENRE);
        urlAdminPattern.add(CommandName.DELETE_GENRE);
        mapConfig.put(RoleEnum.ADMIN, urlAdminPattern);

        List<CommandName> urlUserPattern = new ArrayList<>();
        urlUserPattern.add(CommandName.INDEX);
        urlUserPattern.add(CommandName.ADD_COMMENT);
        mapConfig.put(RoleEnum.USER, urlUserPattern);
    }

    static boolean hasPermission(CommandName command, RoleEnum role) {
        List<CommandName> urlPatterns = mapConfig.get(role);
        return urlPatterns != null && urlPatterns.contains(command);
    }
}
