package ru.yandex.practicum.delivery;


import ru.yandex.practicum.delivery.parcel.Parcel;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final int maximumWeight; //Максимальный вес коробки
    private int currentWeight = 0; //Текущий вес коробки
    private final ArrayList<T> parcels = new ArrayList<>(); //Список посылок в коробку

    public ParcelBox(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    //Добавить посылку в коробку
    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maximumWeight) {
            parcels.add(parcel);
            currentWeight += parcel.getWeight();
        } else if (currentWeight == maximumWeight){
            System.out.println("Коробка уже полная");
        } else {
            System.out.println("В коробку это не поместится");
        }

    }

    public ArrayList<T> getAllParcels(){
        return  parcels;
    }
}
