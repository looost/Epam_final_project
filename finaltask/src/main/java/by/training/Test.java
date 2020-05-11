package by.training;


import by.training.model.AbstractEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
    }

}