package by.training.controller.filter;

import by.training.controller.command.CommandName;
import by.training.model.RoleEnum;

import java.util.*;

/**
 * A class that contains pages that require authentication and authorization.
 */
final class SecurityConfig {
    /**
     * A map that contains {@link RoleEnum} and {@link Set} {@link CommandName} to which they are denied access.
     */
    private static final Map<RoleEnum, Set<CommandName>> MAP_CONFIG = new EnumMap<>(RoleEnum.class);
    /**
     * A set which contains {@link CommandName} that need authentication.
     */
    private static final Set<CommandName> SECURITY_PAGE = new HashSet<>();

    private SecurityConfig() {
    }

    static {
        SECURITY_PAGE.add(CommandName.MY_SERIAL);
        SECURITY_PAGE.add(CommandName.LIKED);
        SECURITY_PAGE.add(CommandName.PROFILE);
        SECURITY_PAGE.add(CommandName.ADMIN_USER);
        SECURITY_PAGE.add(CommandName.ADMIN_GENRE);
        SECURITY_PAGE.add(CommandName.ADMIN_COUNTRY);
        SECURITY_PAGE.add(CommandName.ADMIN_SERIAL);
        SECURITY_PAGE.add(CommandName.ADMIN_STUDIO);
        SECURITY_PAGE.add(CommandName.SAVE_GENRE);
        SECURITY_PAGE.add(CommandName.SAVE_COMMENT);
        SECURITY_PAGE.add(CommandName.SAVE_COUNTRY);
        SECURITY_PAGE.add(CommandName.SAVE_SERIAL);
        SECURITY_PAGE.add(CommandName.SAVE_STUDIO);
        SECURITY_PAGE.add(CommandName.ADD_USER);
        SECURITY_PAGE.add(CommandName.CHANGE_PASSWORD);
        SECURITY_PAGE.add(CommandName.CHANGE_AVATAR);
        SECURITY_PAGE.add(CommandName.CHANGE_ROLE);
        SECURITY_PAGE.add(CommandName.DELETE_GENRE);
        SECURITY_PAGE.add(CommandName.DELETE_COMMENT);
        SECURITY_PAGE.add(CommandName.DELETE_COUNTRY);
        SECURITY_PAGE.add(CommandName.DELETE_SERIAL);
        SECURITY_PAGE.add(CommandName.DELETE_STUDIO);
        SECURITY_PAGE.add(CommandName.WATCH_SERIAL);
        SECURITY_PAGE.add(CommandName.STOP_WATCH_SERIAL);
        SECURITY_PAGE.add(CommandName.LIKE);
        SECURITY_PAGE.add(CommandName.DISLIKE);
    }

    /**
     * The method checks if authentication is needed for this {@link CommandName} or not.
     *
     * @param commandName the command name
     * @return true if {@link CommandName} need authentication and false otherwise
     */
    static boolean isSecurityPage(final CommandName commandName) {
        return SECURITY_PAGE.contains(commandName);
    }

    static {
        Set<CommandName> urlAdminPattern = new HashSet<>();
        urlAdminPattern.add(CommandName.SAVE_COMMENT);
        MAP_CONFIG.put(RoleEnum.ADMIN, urlAdminPattern);

        Set<CommandName> urlModerPattern = new HashSet<>();
        urlModerPattern.add(CommandName.ADMIN_USER);
        urlModerPattern.add(CommandName.CHANGE_ROLE);
        MAP_CONFIG.put(RoleEnum.MODER, urlModerPattern);

        Set<CommandName> urlUserPattern = new HashSet<>();
        urlUserPattern.add(CommandName.ADMIN_USER);
        urlUserPattern.add(CommandName.ADMIN_STUDIO);
        urlUserPattern.add(CommandName.ADMIN_SERIAL);
        urlUserPattern.add(CommandName.ADMIN_COUNTRY);
        urlUserPattern.add(CommandName.ADMIN_GENRE);
        urlUserPattern.add(CommandName.DELETE_STUDIO);
        urlUserPattern.add(CommandName.DELETE_SERIAL);
        urlUserPattern.add(CommandName.DELETE_COUNTRY);
        urlUserPattern.add(CommandName.DELETE_GENRE);
        urlUserPattern.add(CommandName.SAVE_STUDIO);
        urlUserPattern.add(CommandName.SAVE_SERIAL);
        urlUserPattern.add(CommandName.SAVE_COUNTRY);
        urlUserPattern.add(CommandName.SAVE_GENRE);

        MAP_CONFIG.put(RoleEnum.USER, urlUserPattern);
    }

    /**
     * The method checks if this {@link CommandName} is available for this {@link RoleEnum}.
     *
     * @param command {@link CommandName}
     * @param role    {@link RoleEnum}
     * @return true if {@link RoleEnum} have available for {@link CommandName} and false otherwise
     */
    static boolean hasPermission(final CommandName command, final RoleEnum role) {
        Set<CommandName> urlPatterns = MAP_CONFIG.get(role);
        return urlPatterns != null && !urlPatterns.contains(command);
    }
}
