package com.luizmedeirosn.homeads.shared.dto.request;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PostAdDTO(

		@Size(min = 3, max = 50, message = "Enter a title between 3 and 50 characters") String title,

		@Size(min = 50, max = 2000, message = "Enter a description between 50 and 2000 characters") String comment,

		@Pattern(regexp = "^\\d+(\\.\\d{3})*(\\.\\d{1,2})?$") String averagePrice,

		@NotNull Integer rating,

		@NotNull Integer categoryCode,

		@NotNull @DateTimeFormat(iso = ISO.DATE_TIME) String publicationDate,

		@NotNull MultipartFile image,

		@NotNull Long userId) {
}
