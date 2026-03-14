package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.parcel.FragileParcel;
import ru.yandex.practicum.delivery.parcel.Parcel;
import ru.yandex.practicum.delivery.parcel.PerishableParcel;
import ru.yandex.practicum.delivery.parcel.StandardParcel;

public class DeliveryCostTest {

    private final ParcelBox<StandardParcel> parcelBoxStandard = new ParcelBox<>(200); //Коробка стандартных посылок
    private final ParcelBox<FragileParcel> parcelBoxFragile = new ParcelBox<>(100); //Коробка хрупких посылок
    private final ParcelBox<PerishableParcel> parcelBoxPerishable = new ParcelBox<>(50); //Коробка скоропорта
    private final PerishableParcel perishableParcel = new PerishableParcel("Скоропорт", 10, "Дом 2", 10, 5);

    @BeforeEach
    public void beforeEach() {
        //Стандартные посылки
        StandardParcel standardParcel1 = new StandardParcel("Обычная посылка 1", 125, "Дом 5", 10);
        StandardParcel standardParcel2 = new StandardParcel("Обычная посылка 2", 50, "Дом 5/1", 16);

        //Хрупкие посылки
        FragileParcel fragileParcel1 = new FragileParcel("Хрупкая посылка 1", 15, "Перова 2", 5);
        FragileParcel fragileParcel2 = new FragileParcel("Хрупкая посылка 2", 20, "Фитова 15", 7);

        //Посылки скоропорт
        PerishableParcel perishableParcel1 = new PerishableParcel("Посылка скоропорт 1", 16, "Печкова 100", 17, 5);
        PerishableParcel perishableParcel2 = new PerishableParcel("Посылка скоропорт 2", 5, "Духова 1/2", 7, 4);

        parcelBoxStandard.addParcel(standardParcel1);
        parcelBoxStandard.addParcel(standardParcel2);
        parcelBoxFragile.addParcel(fragileParcel1);
        parcelBoxFragile.addParcel(fragileParcel2);
        parcelBoxPerishable.addParcel(perishableParcel1);
        parcelBoxPerishable.addParcel(perishableParcel2);
    }


    //Проверка суммы стандартных посылок
    @Test
    public void shouldCalculateStandardParcelCostCorrectlyTest() {
        int expectedSum = 350;
        Assertions.assertEquals(expectedSum, calculateDeliveryCost(parcelBoxStandard));
    }

    //Проверка суммы хрупких посылок
    @Test
    public void shouldCalculateFragileParcelCostCorrectlyTest() {
        int expectedSum = 140;
        Assertions.assertEquals(expectedSum, calculateDeliveryCost(parcelBoxFragile));
    }

    //Проверка суммы посылок скоропорта
    @Test
    public void shouldCalculatePerishableParcelCostCorrectlyTest() {
        int expectedSum = 63;
        Assertions.assertEquals(expectedSum, calculateDeliveryCost(parcelBoxPerishable));
    }

    //Проверка isExpired скоропорта (Свежее)
    @Test
    public void shouldReturnFalseWhenParcelIsNotExpired() {
        Assertions.assertFalse(perishableParcel.isExpired(13));
    }

    //Проверка isExpired скоропорта (Просрочено)
    @Test
    public void shouldReturnTrueWhenParcelIsExpired() {
        Assertions.assertTrue(perishableParcel.isExpired(20));
    }

    //Проверка isExpired скоропорта (Свежее, граничное значение);
    @Test
    public void shouldReturnFalseWhenIsExpiredOnExactlyExpirationDate() {
        Assertions.assertFalse(perishableParcel.isExpired(15));
    }

    //Добавление в коробку (Успех)
    @Test
    public void shouldAddParcelWhenWeightIsWithinLimit() {
        int size = parcelBoxStandard.getAllParcels().size();
        parcelBoxStandard.addParcel(new StandardParcel("Обычная посылка 3", 20, "Кумова 10", 7));
        Assertions.assertEquals(size + 1, parcelBoxStandard.getAllParcels().size());
    }

    //Добавление в коробку (Переполнена)
    @Test
    public void shouldNotAddParcelWhenWeightExceedsLimit() {
        int size = parcelBoxStandard.getAllParcels().size();
        parcelBoxStandard.addParcel(new StandardParcel("Обычная посылка 3", 200, "Кумова 10", 7));
        Assertions.assertEquals(size, parcelBoxStandard.getAllParcels().size());
    }

    //Добавление в коробку (Граничное значение)
    @Test
    public void shouldAddParcelWhenWeightIsExactlyEqualToMaximum() {
        int size = parcelBoxStandard.getAllParcels().size();
        parcelBoxStandard.addParcel(new StandardParcel("Обычная посылка 3", 25, "Кумова 10", 7));
        Assertions.assertEquals(size + 1, parcelBoxStandard.getAllParcels().size());
    }

    public int calculateDeliveryCost(ParcelBox<? extends Parcel> parcelBox) {
        int sum = 0;

        for (Parcel parcel : parcelBox.getAllParcels()) {
            sum += parcel.calculateDeliveryCost();
        }
        return sum;

    }

}