package by.training.payment.servise;

import by.training.payment.dao.exception.DAOException;
import by.training.payment.dao.factory.DAOFactory;
import by.training.payment.entity.Market;
import by.training.payment.servise.exeception.ServiceException;

public class MarketService {
    private final Creator creator = new Creator();
    private final Parser parser = new Parser();


    public Market getMarket() throws ServiceException {
        Market market = new Market();
        try {
            int size = DAOFactory.getInstance().getDao().readData().size();
            for (int i = 0; i < size; i++) {
                creator.createMarket(market, parser.parsFile(i)[0],
                        Double.parseDouble(parser.parsFile(i)[1]));
            }
            return market;
        } catch (DAOException e) {
            throw new ServiceException("Файл не найден!", e);
        }
    }
}
