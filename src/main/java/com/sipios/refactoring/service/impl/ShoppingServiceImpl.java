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
        double price = 0;

        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        if (body.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double discount = body.getType().getDiscount();

        // Compute total amount depending on the types and quantity of product and
        // if we are in winter or summer discounts periods
        if (
            !(
                cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                    cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                    cal.get(Calendar.MONTH) == 5
            ) &&
                !(
                    cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                        cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                        cal.get(Calendar.MONTH) == 0
                )
        ) {
            if (body.getItems() == null) {
                return 0;
            }

            for (int i = 0; i < body.getItems().length; i++) {
                Item it = body.getItems()[i];

                switch (it.getType()) {
                    case DRESS:
                        price += 50 * it.getNb() * discount;
                        break;
                    case JACKET:
                        price += 100 * it.getNb() * discount;
                        break;
                    case TSHIRT:
                        price += 30 * it.getNb() * discount;
                        break;
                    default:
                        break;
                }

            }
        } else {
            if (body.getItems() == null) {
                return 0;
            }

            for (int i = 0; i < body.getItems().length; i++) {
                Item it = body.getItems()[i];

                switch (it.getType()) {
                    case TSHIRT:
                        price += 30 * it.getNb() * discount;
                        break;
                    case DRESS:
                        price += 50 * it.getNb() * 0.8 * discount;
                        break;
                    case JACKET:
                        price += 100 * it.getNb() * 0.9 * discount;
                        break;
                    default:
                        break;
                }
            }
        }

        try {
            if (price > body.getType().getMaxPrice()) {
                throw new Exception("Price (" + price + ") is too high for " + body.getType().getText());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return discount;
    }
}
