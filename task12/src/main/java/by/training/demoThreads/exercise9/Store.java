package by.training.demoThreads.exercise9;

class Store {
    int counter = 0; // счетчик товаров
    final int N = 5; // максимально допустимое число

    // синхронизированный метод для производителей
    synchronized int put() {
        if (counter < N) //если товаров меньше
        {
            counter++; // кладем товар
            System.out.println("склад имеет " + counter + " товар(ов)");
            return 1; // в случае удачного выполнения возвращаем 1
        }
        return 0;// в случае неудачного выполнения возвращаем 0
    }

    // метод для покупателей
    synchronized int get() {
        if (counter > 0) //если хоть один товар присутствует
        {
            counter--; //берем товар
            System.out.println("склад имеет " + counter + " товар(ов)");
            return 1;// в случае удачного выполнения возвращаем 1
        }
        return 0;// в случае неудачного выполнения возвращаем 0
    }
}

