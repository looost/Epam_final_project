package by.training.abiturientV2.dao;


public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final AbiturientDAO fileAbiturientDAO = new FileAbiturientDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AbiturientDAO getFileAbiturientDAO() {
        return fileAbiturientDAO;
    }

}
