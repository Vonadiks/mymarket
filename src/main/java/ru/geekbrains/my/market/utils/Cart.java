package ru.geekbrains.my.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.my.market.dto.OrderItemDto;
import ru.geekbrains.my.market.model.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;
    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public void clear() {
        items.clear();
        price = BigDecimal.ZERO;
    }

    public boolean add(Long productId) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;

    }

    public void add(Product product) {

        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto oid : items) {
            price = price.add(oid.getPrice());
        }
    }

    public void delete(Long productId) {
        items.removeIf(oi -> oi.getProductId().equals(productId));
        recalculate();
//        for (OrderItemDto o : items) {
//            if ((o.getProductId().equals(productId))&&(o.getQuantity()>0)) {
//                o.changeQuantity(-1);
//                recalculate();
//                return true;
//            }
//        }
//        return false;

    }

    public void delete(Product product) {
        items.remove(new OrderItemDto(product));
        recalculate();
    }

    public boolean changeQuantity(Long productId, int amount) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(amount);
                if (o.getQuantity() <=0) {
                    iter.remove();
                }
                recalculate();
                return true;
            }
        }
        return false;
    }


}
