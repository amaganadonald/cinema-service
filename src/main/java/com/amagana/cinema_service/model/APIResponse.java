package com.amagana.cinema_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private int status;
    private String message;
    private List<ErrorsResponse> errors;
    private T results;

    public static <T>APIResponse<T> apiResponseMessage(int status, String message) {
        return APIResponse.<T>builder()
                           .status(status)
                           .message(message)
                           .build();
    }

    public static <T>APIResponse<T> apiResponseErrors(int status, List<ErrorsResponse> errors) {
        return APIResponse.<T>builder()
                           .status(status)
                           .errors(errors)
                           .build();
    }

    public static <T>APIResponse<List<T>> apiResponseListResult(int status, String message, List<T> results) {
        return APIResponse.<List<T>>builder()
                           .status(status)
                           .message(message)
                           .results(results)
                           .build();
    }

    public static <T>APIResponse<T> apiResponseSingleResult(int status, String message, T results) {
        return APIResponse.<T>builder()
                           .status(status)
                           .message(message)
                           .results(results)
                           .build();
    }


}
