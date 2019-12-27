package by.training.abiturient.entity;

import java.util.ArrayList;

public class AbiturientBean {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String mobileNumber;
    private ArrayList<Integer> grades;
    private int firstGrade;
    private int secondGrade;
    private int thirdGrade;


    //    public AbiturientBean(int id, String firstName, String lastName, String middleName, String address, String mobileNumber, ArrayList<Integer> grades) {
//        this.id = id;
//        this.first_name = firstName;
//        this.last_name = lastName;
//        this.middle_name = middleName;
//        this.address = address;
//        this.mobile_number = mobileNumber;
//        this.grades = grades;
//    }

    public AbiturientBean(int id, String lastName, String firstName, String middleName,
                          String address, String mobileNumber, int firstGrade, int secondGrade, int thirdGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.firstGrade = firstGrade;
        this.secondGrade = secondGrade;
        this.thirdGrade = thirdGrade;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getAverage() {
        return (double) (getFirstGrade() + getSecondGrade() + getThirdGrade()) / 3;
    }

    public int getSumGrade() {
        return getFirstGrade() + getSecondGrade() + getThirdGrade();
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbiturientBean that = (AbiturientBean) o;

        if (id != that.id) return false;
        if (firstGrade != that.firstGrade) return false;
        if (secondGrade != that.secondGrade) return false;
        if (thirdGrade != that.thirdGrade) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(that.mobileNumber) : that.mobileNumber != null)
            return false;
        return grades != null ? grades.equals(that.grades) : that.grades == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
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

//    @Override
//    public String toString() {
//        return getId() + " - " + getFirst_name() + " " + getLast_name() + " " + getMiddle_name() +
//                ", Адрес: " + getAddress() + ", Телефон: " + getMobile_number() +
//                ", Первая оценка: " + getGrades().get(0) + ", Вторая оценка: " + getGrades().get(1) +
//                ", Третья оценка: " + getGrades().get(2) + ", Сумма баллов: " + TestService.sumGrade(this);
//    }
}
