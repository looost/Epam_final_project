package by.training.abiturientV2.service;

import by.training.abiturientV2.dao.AbiturientDAO;
import by.training.abiturientV2.dao.DAOFactory;
import by.training.abiturientV2.entity.Abiturient;

import java.util.stream.Stream;

public class AbiturientServiceImpl implements AbiturientService {

    AbiturientDAO abiturientDAO = DAOFactory.getInstance().getFileAbiturientDAO();

    @Override
    public Stream<Abiturient> abiturientsWithUnsatisfactoryGrades() {
        return abiturientDAO.abiturientsWithUnsatisfactoryGrades();
    }

    @Override
    public Stream<Abiturient> abiturientsWithHigherGrade(int grade) {
        return abiturientDAO.abiturientsWithHigherGrade(grade);
    }

    @Override
    public Stream<Abiturient> sortBySumGradeWorst(int limit) {
        return abiturientDAO.sortBySumGradeWorst(limit);
    }

    @Override
    public Stream<Abiturient> sortBySumGradeBest(int limit) {
        return abiturientDAO.sortBySumGradeBest(limit);
    }

    @Override
    public Stream<Abiturient> sortByLastName() {
        return abiturientDAO.sortByLastName();
    }
}
