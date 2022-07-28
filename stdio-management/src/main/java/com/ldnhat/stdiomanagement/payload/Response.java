package com.ldnhat.stdiomanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private List<T> content;
    private int number;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
}
