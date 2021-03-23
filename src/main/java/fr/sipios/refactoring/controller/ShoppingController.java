package fr.sipios.refactoring.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    @PostMapping
    public String getPrice(@RequestBody Item[] body) {
        int price = 0;

        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        if (
            !(
                cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                cal.get(Calendar.MONTH) == 5
            ) ||
            !(
                cal.get(Calendar.DAY_OF_MONTH) < 15 &&
                cal.get(Calendar.DAY_OF_MONTH) > 5 &&
                cal.get(Calendar.MONTH) == 0
            )
        ) {
            for (int i = 0; i < body.length; i++) {
                Item it = body[i];

                if (it.getType().equals("TSHIRT")) {
                    price += 30 * it.getQuantity();
                } else if (it.getType().equals("DRESS")) {
                    price += 50 * it.getQuantity();
                } else if (it.getType().equals("JACKET")) {
                    price += 100 * it.getQuantity();
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getQuantity();
                // }
            }
        } else {
            for (int i = 0; i < body.length; i++) {
                Item it = body[i];

                if (it.getType().equals("TSHIRT")) {
                    price += 30 * it.getQuantity();
                } else if (it.getType().equals("DRESS")) {
                    price += 50 * it.getQuantity() * 0.2;
                } else if (it.getType().equals("JACKET")) {
                    price += 100 * it.getQuantity() * 0.1;
                }
                // else if (it.getType().equals("SWEATSHIRT")) {
                //     price += 80 * it.getQuantity();
                // }
            }
        }

        return String.valueOf(price);
    }
}

class Item {

    private String type;
    private int quantity;

    public Item() {}

    public Item(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
