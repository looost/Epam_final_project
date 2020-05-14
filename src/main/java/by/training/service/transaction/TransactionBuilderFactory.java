package by.training.service.transaction;

import by.training.dao.Transaction;
import by.training.service.exception.ServiceException;

import java.util.List;

/**
 * Class is designed to handle {@link TransactionBuilder}.
 */
public final class TransactionBuilderFactory {

    private TransactionBuilderFactory() {
    }

    /**
     * A class that is designed to handle a single result.
     *
     * @param <T>     the {@link by.training.model.AbstractEntity}
     * @param builder the Transaction builder
     * @return the Transaction builder
     */
    public static <T> TransactionBuilder<T> getSingleTransactionBuilder(final TransactionBuilder<T> builder) {
        return new TransactionBuilder<T>() {
            @Override
            public T transaction(final Transaction t, final T entity) throws ServiceException {
                return builder.transaction(t, entity);
            }
        };
    }

    /**
     * A class that is designed to handle a many result.
     *
     * @param <T>     the {@link by.training.model.AbstractEntity}
     * @param builder the Transaction builder
     * @return the Transaction builder
     */
    public static <T> TransactionBuilder<List<T>> getListTransactionBuilder(final TransactionBuilder<T> builder) {
        return new TransactionBuilder<List<T>>() {
            @Override
            public List<T> transaction(final Transaction t, final List<T> entity) throws ServiceException {
                for (T e : entity
                ) {
                    builder.transaction(t, e);
                }
                return entity;
            }
        };
    }
}
