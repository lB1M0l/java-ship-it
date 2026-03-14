package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    protected String description; //Краткое описание посылки
    protected int weight; //Вес посылки
    protected String deliveryAddress; //Адрес получения посылки
    protected int sendDay; //День отправки

    public static final int RATE_STANDARD = 2;
    public static final int RATE_PERISHABLE = 3;
    public static final int RATE_FRAGILE = 4;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    //Упаковка посылки
    public void packageItem() {
        System.out.println("Посылка \"" + description + "\" упакована");
    }

    //Доставка посылки
    public void deliver() {
        System.out.println("Посылка \"" + description + " доставлена по адресу " + deliveryAddress);
    }

    //Метод для получения множителя
    public abstract int getRate();

    public int calculateDeliveryCost(){
        return weight * getRate();
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }
}
