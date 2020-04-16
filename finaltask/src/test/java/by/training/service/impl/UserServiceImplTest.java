package by.training.service.impl;

import by.training.model.User;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceImplTest {

    @Test
    public void testFindByLogin() throws ServiceException {
        User user = ServiceFactory.getInstance().getUserService().findByLogin("admin");
    }

    @Test
    public void testFindByLoginAndPassword() {
    }

    @Test
    public void testCreateUserWithRole() {
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testUpdate() {
    }
}