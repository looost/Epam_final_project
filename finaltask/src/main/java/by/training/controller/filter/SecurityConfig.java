package by.training.controller.filter;

import by.training.controller.command.CommandName;
import by.training.model.RoleEnum;

import java.util.*;

/**
 * A class that contains pages that require authentication and authorization.
 */
final class SecurityConfig {
    /**
     * A map that contains {@link RoleEnum} and {@link List} {@link CommandName} to which they are denied access.
     */
    private static final Map<RoleEnum, List<CommandName>> MAP_CONFIG = new EnumMap<>(RoleEnum.class);
    /**
     * A set which contains {@link CommandName} that need authentication.
     */
    private static final Set<CommandName> SECURITY_PAGE = new HashSet<>();

    private SecurityConfig() {
    }

    static {
        SECURITY_PAGE.add(CommandName.SAVE_COMMENT);
        //SECURITY_PAGE.add(CommandName.ADMIN_USER);
        SECURITY_PAGE.add(CommandName.MY_SERIAL);
        SECURITY_PAGE.add(CommandName.LIKED);
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
        List<CommandName> urlAdminPattern = new ArrayList<>();
        urlAdminPattern.add(CommandName.SAVE_COMMENT);
        MAP_CONFIG.put(RoleEnum.ADMIN, urlAdminPattern);

        List<CommandName> urlModerPattern = new ArrayList<>();
        urlModerPattern.add(CommandName.ADMIN_USER);
        MAP_CONFIG.put(RoleEnum.MODER, urlModerPattern);

        List<CommandName> urlUserPattern = new ArrayList<>();
        urlUserPattern.add(CommandName.ADMIN_USER);
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
        List<CommandName> urlPatterns = MAP_CONFIG.get(role);
        return urlPatterns != null && !urlPatterns.contains(command);
    }
}
