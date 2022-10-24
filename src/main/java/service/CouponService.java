package service;

import model.MenuItem;
import model.Restaurant;

import java.util.List;

public interface CouponService {
    List<Restaurant> resInfo();
    List<MenuItem> itemInfo(int resId);

    Boolean addCoupon(String name, String scope, String resId, String itemId, String minMoney, String value, String description, String image);
}
