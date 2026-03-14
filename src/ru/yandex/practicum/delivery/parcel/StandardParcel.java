package ru.yandex.practicum.delivery.parcel;

//Стандартная посылка
public class StandardParcel extends Parcel {

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getRate() {
        return RATE_STANDARD;
    }
}
