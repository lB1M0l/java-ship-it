package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> allTrackables = new ArrayList<>();
    private static final ParcelBox<StandardParcel> parcelBoxStandard = new ParcelBox<>(200); //Коробка стандартных посылок
    private static final ParcelBox<FragileParcel> parcelBoxFragile = new ParcelBox<>(100); //Коробка хрупких посылок
    private static final ParcelBox<PerishableParcel> parcelBoxPerishable = new ParcelBox<>(50); //Коробка посылок скоропорта

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    setNewLocal();
                    break;
                case 5:
                    getAllParcels();
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Сменить локацию отслеживаемых посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже
    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Выберите тип посылки: ");
        System.out.println("""
                1 - Стандартная
                2 - Хрупкая
                3 - Скоропорт""");
        int command = Integer.parseInt(scanner.nextLine());

        String description; //Краткое описание посылки
        int weight; //Вес посылки
        String deliveryAddress; //Адрес получения посылки
        int sendDay; //День отправки

        System.out.println("Введите описание посылки: ");
        description = scanner.nextLine();
        System.out.println("Введите вес посылки: ");
        weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес доставки: ");
        deliveryAddress = scanner.nextLine();
        System.out.println("Введите день отправки: ");
        sendDay = Integer.parseInt(scanner.nextLine());

        switch (command) {
            case 1:
                //Обработка стандартной посылки
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                parcelBoxStandard.addParcel(standardParcel);
                break;
            case 2:
                //Обработка хрупкой посылки
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                allTrackables.add(fragileParcel);
                parcelBoxFragile.addParcel(fragileParcel);
                break;
            case 3:
                //Обработка посылки скоропорта
                System.out.println("Введите срок хранения товара: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                parcelBoxPerishable.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Команда не распознана!");
                break;
        }

    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;

        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Сумма всех заказов: " + sum);
    }

    private static void setNewLocal(){
        System.out.println("Введите новую локацию: ");
        String newLocal = scanner.nextLine();

        for (Trackable trackable : allTrackables) {
            trackable.reportStatus(newLocal);
        }
    }

    private static void getAllParcels(){
        System.out.println("Выберите коробку:");
        System.out.println("1 - Стандартная" +
                "2 - Хрупкая" +
                "3 - Скоропорт");
        int command = Integer.parseInt(scanner.nextLine());

        switch (command) {
            case 1:
                printAllParcels(parcelBoxStandard);
                break;
            case 2:
                printAllParcels(parcelBoxFragile);
                break;
            case 3:
                printAllParcels(parcelBoxPerishable);
                break;
            default:
                System.out.println("Команда не распознана!");
        }
    }

    private static void printAllParcels(ParcelBox<? extends Parcel> box){
        for(Parcel parcel : box.getAllParcels()){
            System.out.println(parcel.getDescription());
        }
    }

}

