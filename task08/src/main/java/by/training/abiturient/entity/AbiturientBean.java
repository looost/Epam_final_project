package by.training.abiturient.entity;

import by.training.abiturient.service.TestService;

import java.util.ArrayList;

public class AbiturientBean {
    private int id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String address;
    private int mobile_number;
    private ArrayList<Integer> grades;
    private int first_grade;
    private int second_grade;
    private int third_grade;

    public int getFirst_grade() {
        return first_grade;
    }

    public void setFirst_grade(int first_grade) {
        this.first_grade = first_grade;
    }

    public int getSecond_grade() {
        return second_grade;
    }

    public void setSecond_grade(int second_grade) {
        this.second_grade = second_grade;
    }

    public int getThird_grade() {
        return third_grade;
    }

    public void setThird_grade(int third_grade) {
        this.third_grade = third_grade;
    }

//    public AbiturientBean(int id, String first_name, String last_name, String middle_name, String address, int mobile_number, ArrayList<Integer> grades) {
//        this.id = id;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.middle_name = middle_name;
//        this.address = address;
//        this.mobile_number = mobile_number;
//        this.grades = grades;
//    }

    public AbiturientBean(int id, String last_name, String first_name, String middle_name,
                          String address, int mobile_number, int first_grade, int second_grade, int third_grade) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.address = address;
        this.mobile_number = mobile_number;
        this.first_grade = first_grade;
        this.second_grade = second_grade;
        this.third_grade = third_grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public double getAverage() {
        return (double) (getFirst_grade()+getSecond_grade()+getThird_grade())/3;
    }

    public int getSumGrade() {
        return getFirst_grade() + getSecond_grade() + getThird_grade();
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
        if (mobile_number != that.mobile_number) return false;
        if (first_grade != that.first_grade) return false;
        if (second_grade != that.second_grade) return false;
        if (third_grade != that.third_grade) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (middle_name != null ? !middle_name.equals(that.middle_name) : that.middle_name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return grades != null ? grades.equals(that.grades) : that.grades == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (middle_name != null ? middle_name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + mobile_number;
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
        result = 31 * result + first_grade;
        result = 31 * result + second_grade;
        result = 31 * result + third_grade;
        return result;
    }

    @Override
    public String toString() {
        return getId() + " - " + getLast_name() + " " + getFirst_name() + " " + getMiddle_name() +
                ", Адрес: " + getAddress() + ", Телефон: " + getMobile_number() +
                ", Первая оценка: " + getFirst_grade() + ", Вторая оценка: " + getSecond_grade() +
                ", Третья оценка: " + getThird_grade() + ", Сумма баллов: " + getSumGrade();
    }

//    @Override
//    public String toString() {
//        return getId() + " - " + getFirst_name() + " " + getLast_name() + " " + getMiddle_name() +
//                ", Адрес: " + getAddress() + ", Телефон: " + getMobile_number() +
//                ", Первая оценка: " + getGrades().get(0) + ", Вторая оценка: " + getGrades().get(1) +
//                ", Третья оценка: " + getGrades().get(2) + ", Сумма баллов: " + TestService.sumGrade(this);
//    }
}
