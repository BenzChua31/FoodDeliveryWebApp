package service.impl;

import mapper.CouponMapper;
import model.MenuItem;
import model.Restaurant;
import service.CouponService;
import utils.SqlSessionUtil;

import java.util.List;

public class CouponServiceImpl implements CouponService{
    @Override
    public List<Restaurant> resInfo() {
        return SqlSessionUtil.openSqlSession().selectList("selectResInfo");
    }

    @Override
    public List<MenuItem> itemInfo(int resId) { return SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class).selectItemInfoByResId(resId); }

    @Override
    public Boolean addCoupon(String name, String scope, String resId, String itemId, String minMoney, String value, String description) {
        int intScope = 0;
        if (scope.equals("Specific store"))
        {
            intScope = 1;

        }
        else if (scope.equals("Specific items in specific store"))
        {
            intScope = 2;

        }
        return true;
    }
}
