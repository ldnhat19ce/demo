package com.ldnhat.stdiomanagement.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response<T> {

    private List<T> content;
    private int number;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
}
