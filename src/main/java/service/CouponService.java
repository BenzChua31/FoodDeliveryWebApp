package service;

import model.MenuItem;
import model.Restaurant;

import java.util.List;

public interface CouponService {
    List<Restaurant> resInfo();

    List<MenuItem> itemInfo(int resId);
}
