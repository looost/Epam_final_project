package by.training.main;

public class Test {
    private final int reqFieldOne;
    private final int reqFieldTwo;
    private final int optFieldOne;
    private final int optFieldTwo;
    private final int optFieldThird;
    private final int optFieldFour;

    public static class Builder {
        // Обязательные параметры
        private final int reqFieldOne;
        private final int reqFieldTwo;

        // Необязательные параметры с значениями по умолчанию
        private int optFieldOne = 0;
        private int optFieldTwo = 0;
        private int optFieldThird = 0;
        private int optFieldFour = 0;

        public Builder(int reqFieldOne, int reqFieldTwo) {
            this.reqFieldOne = reqFieldOne;
            this.reqFieldTwo = reqFieldTwo;
        }

        public Builder optFieldOne(int val) {
            optFieldOne = val;
            return this;
        }

        public Builder optFieldTwo(int val) {
            optFieldTwo = val;
            return this;
        }

        public Builder optFieldThird(int val) {
            optFieldThird = val;
            return this;
        }

        public Builder optFieldFour(int val) {
            optFieldFour = val;
            return this;
        }

        public Test buidl() {
            return new Test(this);
        }
    }

    public Test(Builder builder) {
        reqFieldOne = builder.reqFieldOne;
        reqFieldTwo = builder.reqFieldTwo;
        optFieldOne = builder.optFieldOne;
        optFieldTwo = builder.optFieldTwo;
        optFieldThird = builder.optFieldThird;
        optFieldFour = builder.optFieldFour;
    }
}