package by.training.utils;

public class ConstantName {

    private ConstantName() {
    }

    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_NEW_PASSWORD = "newPassword";
    public static final String PARAMETER_ROLE = "role";
    public static final String PARAMETER_LANGUAGE = "language";
    public static final String PARAMETER_QUERY = "query";
    public static final String PARAMETER_COUNT_ALL_SERIALS = "countAllSerial";
    public static final String PARAMETER_COUNTRY = "country";
    public static final String PARAMETER_COUNT_ALL_COUNTRY = "countAllCountry";
    public static final String PARAMETER_GENRE = "genre";
    public static final String PARAMETER_GENRES = "genres";
    public static final String PARAMETER_COUNT_ALL_GENRES = "countAllGenre";
    public static final String PARAMETER_STUDIO = "studio";
    public static final String PARAMETER_COUNT_ALL_STUDIOS = "countAllStudio";
    public static final String PARAMETER_COMMENT = "comment";
    public static final String PARAMETER_LAST_SHOWS = "last";
    public static final String PARAMETER_PAGE = "page";
    public static final String PARAMETER_ITEM_ON_PAGE = "itemsOnPage";
    public static final String PARAMETER_COMMENT_ID = "commentId";

    public static final String ATTRIBUTE_ERROR = "error";
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_GENRES = PARAMETER_GENRES;
    public static final String ATTRIBUTE_GENRE_PROBLEM = "genreProblem";
    public static final String ATTRIBUTE_COMMENT_PROBLEM = "commentProblem";
    public static final String ATTRIBUTE_COUNTRY_PROBLEM = "countryProblem";
    public static final String ATTRIBUTE_STUDIO_PROBLEM = "studioProblem";
    public static final String ATTRIBUTE_COUNTRY = PARAMETER_COUNTRY;
    public static final String ATTRIBUTE_STUDIO = PARAMETER_STUDIO;
    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_USER_ROLE = "role";
    public static final String ATTRIBUTE_SHOW = "show";
    public static final String ATTRIBUTE_SHOWS = "shows";
    public static final String ATTRIBUTE_STATUS_CODE = "statusCode";
    public static final String ATTRIBUTE_SEARCH_FORM = "searchForm";
    public static final String ATTRIBUTE_WATCH_STATUS = "watchStatus";

    public static final String HEADER_REFERER = "referer";

    public static final int COUNT_SERIAL_IN_MAIN_PAGE = 6;
    public static final int COUNT_GENRE_IN_ADMIN_PAGE = 6;
    public static final int COUNT_COUNTY_IN_ADMIN_PAGE = 6;
    public static final int COUNT_STUDIO_IN_ADMIN_PAGE = 6;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int COUNT_LATEST_SHOWS = 4;
    public static final int MAX_COMMENT_LENGTH = 512;
    /*
    forward jsp pages
     */
    public static final String ROUTING_INDEX_JSP = "index.jsp";
    public static final String ROUTING_SIGN_UP_JSP = "signup2.jsp";
    public static final String ROUTING_PROFILE_JSP = "profile.jsp";
    public static final String ROUTING_REGISTRATION_JSP = "registration.jsp";
    public static final String ROUTING_SEARCH_JSP = "search.jsp";
    public static final String ROUTING_SHOW_JSP = "show.jsp";
    public static final String ROUTING_MY_SHOW_JSP = "my-shows.jsp";
    public static final String ROUTING_ADMIN_SERIAL_JSP = "/admin/serial.jsp";
    public static final String ROUTING_ADMIN_COUNTRY_JSP = "/admin/country.jsp";
    public static final String ROUTING_ADMIN_GENRE_JSP = "/admin/genre.jsp";
    public static final String ROUTING_ADMIN_STUDIO_JSP = "/admin/studio.jsp";
    public static final String ROUTING_ADMIN_USER_JSP = "/admin/user.jsp";
    public static final String ROUTING_ERROR_JSP = "error.jsp";
    public static final String ROUTING_NOT_FOUND_JSP = "not-found.jsp";
    /*
    redirect pages
     */
    public static final String ROUTING_INDEX_PAGE = "/final/index.html";
    public static final String ROUTING_LOGIN_PAGE = "/final/login.html";
    public static final String ROUTING_REGISTRATION_PAGE = "/final/registration.html";
    public static final String ROUTING_PROFILE_PAGE = "/final/profile.html";
    public static final String ROUTING_SHOW_PAGE = "/final/show.html";
    public static final String ROUTING_SERIAL_PAGE = "/final/admin/serial.html";
    public static final String ROUTING_GENRE_PAGE = "/final/admin/genre.html";
    public static final String ROUTING_COUNTRY_PAGE = "/final/admin/country.html";
    public static final String ROUTING_STUDIO_PAGE = "/final/admin/studio.html";
    public static final String ROUTING_USER_PAGE = "/final/admin/user.html";
    public static final String ROUTING_ERROR_PAGE = "/final/error.html";
    /*
    logger name
     */
    public static final String DEBUG_LOGGER = "debug";
    public static final String INFO_LOGGER = "info";
    public static final String ERROR_LOGGER = "error";
    /*
    for test
     */
    public static final String PATH_TO_PROPERTY_FILE = "src\\test\\resources\\pool.properties";
}
