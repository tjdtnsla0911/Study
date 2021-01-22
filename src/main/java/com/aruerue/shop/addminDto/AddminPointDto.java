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
public class AddminPointDto {
private String username;
private String name;
//private int total_amount;
private Timestamp createDate;
private int id;
private int userId;
private Date historyDate;
private String reason;
private int point;
private int totalPoint;
private int upPoint;
private int minusPoint;
private int userTotalPoint;
}