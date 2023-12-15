package com.iba.fertilizersmanager.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto <D> {

    private List<D> objects;

    private int totalPages;

    private long totalElements;

}
