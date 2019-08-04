package com.per.iroha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advice {

    private int id;

    private String date;

    private String fromUsername;

    private String advice;
}
