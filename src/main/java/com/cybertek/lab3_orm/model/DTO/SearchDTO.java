package com.cybertek.lab3_orm.model.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    private String productName;

    private String categoryName;
}
