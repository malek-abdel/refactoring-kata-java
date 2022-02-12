package com.sipios.refactoring.service.impl;

import com.sipios.refactoring.model.Body;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.service.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class ShoppingServiceImpl implements ShoppingService {


    @Override
    public double calculateItemsPrice(Body body) {
        double totalPrice = 0;

        if (body.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (body.getItems() == null) {
            return totalPrice;
        }


        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        double discount = body.getType().getDiscount();

        // Compute total amount depending on the types and quantity of product and
        // if we are in winter or summer discounts periods
        if (isDiscountPeriod(cal)) {

            for (Item it : body.getItems()) {
                totalPrice += it.getType().getPrice() * it.getNb() * discount * it.getType().getDiscountPeriodRate();
            }
        } else {
            for (Item it : body.getItems()) {
                totalPrice += it.getType().getPrice() * it.getNb() * discount;
            }
        }

        if (totalPrice > body.getType().getMaxPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price (" + totalPrice + ") is too high for " + body.getType().getText());
        }


        return totalPrice;
    }

    private static boolean isDiscountPeriod(Calendar calendarDate) {
        return (calendarDate.get(Calendar.DAY_OF_MONTH) < 15 &&
            calendarDate.get(Calendar.DAY_OF_MONTH) > 5 &&
            (calendarDate.get(Calendar.MONTH) == Calendar.JUNE) ||
            calendarDate.get(Calendar.MONTH) == Calendar.JANUARY);
    }

}
