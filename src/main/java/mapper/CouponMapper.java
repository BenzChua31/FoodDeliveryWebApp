package mapper;

import java.util.List;
import model.Coupon;
import model.MenuItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon row);

    Coupon selectByPrimaryKey(Integer couponId);

    List<Coupon> selectAll();

    List<MenuItem> selectItemInfoByResId(int resId);

    int updateByPrimaryKey(Coupon row);

    @Insert("insert into Coupon_R values(#{couponId},#{resId})")
    int insertCouponR(@Param("couponId")int couponId, @Param(("resId"))int resId);

    @Insert("insert into Coupon_Item values(#{couponId},#{itemId})")
    int insertCouponItem(@Param("couponId")int couponId, @Param(("itemId"))int itemId);
}