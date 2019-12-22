package by.training.abiturient.entity;

public class AbiturientBean {
    private int id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String address;
    private int mobile_number;
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

    public AbiturientBean(int id, String first_name, String last_name, String middle_name,
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getId() + " - " + getFirst_name() + " " + getLast_name() + " " + getMiddle_name() +
                ", Адрес: " + getAddress() + ", Телефон: " + getMobile_number() +
                ", Первая оценка: " + getFirst_grade() + ", Вторая оценка: " + getSecond_grade() +
                ", Третья оценка: " + getThird_grade() + ", Средний балл: " + getAverage() ;
    }
}
