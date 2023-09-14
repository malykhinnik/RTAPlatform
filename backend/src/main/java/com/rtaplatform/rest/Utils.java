package com.rtaplatform.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Utils {
    public static PageRequest createPageRequest(final Integer page,
                                                final Integer size,
                                                final String sortDir,
                                                final String sortBy) {
        return PageRequest.of(page, size, createSort(sortDir, sortBy));
    }

    public static Sort createSort(final String sortDir,
                                  final String sortBy) {
        return Sort.Direction.DESC.name().equalsIgnoreCase(sortDir) ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
    }
}
