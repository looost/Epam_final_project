package by.training.abiturientV2.entity;

public class Abiturient extends Person {
    private int id;
    private int firstGrade;
    private int secondGrade;
    private int thirdGrade;

    public Abiturient(int id, String firstName, String lastName, String middleName, String address, String mobileNumber, int firstGrade, int secondGrade, int thirdGrade) {
        super(firstName, lastName, middleName, address, mobileNumber);
        this.id = id;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(int firstGrade) {
        this.firstGrade = firstGrade;
    }

    public int getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(int secondGrade) {
        this.secondGrade = secondGrade;
    }

    public int getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(int thirdGrade) {
        this.thirdGrade = thirdGrade;
    }

    public int getSumGrade() {
        return getFirstGrade() + getSecondGrade() + getThirdGrade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Abiturient that = (Abiturient) o;

        if (id != that.id) return false;
        if (firstGrade != that.firstGrade) return false;
        if (secondGrade != that.secondGrade) return false;
        return thirdGrade == that.thirdGrade;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstGrade;
        result = 31 * result + secondGrade;
        result = 31 * result + thirdGrade;
        return result;
    }

    @Override
    public String toString() {
        return getId() + " - " + getLastName() + " " + getFirstName() + " " + getMiddleName() +
                ", Адрес: " + getAddress() + ", Телефон: " + getMobileNumber() +
                ", Первая оценка: " + getFirstGrade() + ", Вторая оценка: " + getSecondGrade() +
                ", Третья оценка: " + getThirdGrade() + ", Сумма баллов: " + getSumGrade();
    }
}
