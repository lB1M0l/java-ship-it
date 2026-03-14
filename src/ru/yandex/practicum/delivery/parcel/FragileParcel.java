package ru.yandex.practicum.delivery.parcel;

//Хрупкая посылка
public class FragileParcel extends Parcel implements Trackable{

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    //Доставка посылки, но сначала упаковка
    @Override
    public void packageItem() {
        System.out.println("Посылка \"" + description + "\" обернута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public int getRate() {
        return RATE_FRAGILE;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка \"" + description + " изменила местоположение на  " + newLocation);
    }
}
