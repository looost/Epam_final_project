package by.training.exerciseaccount.entity;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private int idClient;
    private String name;
    private List <Account> account = new ArrayList<>();

    public Client(int idClient, String name) {
        this.idClient = idClient;
        this.name = name;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountList() {
        return account;
    }

    public void setAccount(Account account) {
        if (account.getAccountId() == this.idClient) {
            this.account.add(account);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return account != null ? account.equals(client.account) : client.account == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
