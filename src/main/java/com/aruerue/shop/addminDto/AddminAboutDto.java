package com.aruerue.shop.addminDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//굳이 addmin안만들어도될꺼같긴한데 그래도 만듬 ^_^
public class AddminAboutDto {
	private int id;
	private String subject;
	private String content;

}
