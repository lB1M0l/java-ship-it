package ru.yandex.practicum.delivery.parcel;

//Скоропорт посылка
public class PerishableParcel extends  Parcel{
    protected int timeToLive; //Срок хранения

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getRate() {
        return RATE_PERISHABLE;
    }


    //Проверка на сроки продукта
    public boolean isExpired (int currentDay){
        return (timeToLive + sendDay) < currentDay;
    }
}
