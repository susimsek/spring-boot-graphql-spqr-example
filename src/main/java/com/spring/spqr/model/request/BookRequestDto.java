package com.spring.spqr.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {

    String title;

    String author;

    String releaseDate;
}
