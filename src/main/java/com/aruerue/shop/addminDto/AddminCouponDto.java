package com.aruerue.shop.addminDto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddminCouponDto {
private int id;
private int userId;
private String code;
private Date validityStart;
private Date validityEnd;
private boolean availability;
private int salePrice;
private String reason;
private int count;
////////////↑ 까지는 coupon꺼

////////////↓ 여기는 user꺼
private String username;
private String name;
private String gender;
private String address;
private String detail_address;
private Timestamp createDate;
private int whatCoupons;

////////// availability 체인지할꺼
private String ChangeAvailability;

}
