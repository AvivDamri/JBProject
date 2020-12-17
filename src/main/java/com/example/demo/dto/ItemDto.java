package com.example.demo.dto;

import com.example.demo.beans.ItemType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto implements Serializable {

    private Long id;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Timestamp createdDate;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Timestamp lastModifiedDate;

    @NotNull
    private String itemName;

    @NotNull
    private ItemType itemType;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;
}
