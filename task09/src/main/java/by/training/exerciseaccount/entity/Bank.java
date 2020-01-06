package by.training.exerciseaccount.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    String bankName;
    List <Client> clients = new ArrayList<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setClients(Client client) {
        this.clients.add(client);
    }
}
