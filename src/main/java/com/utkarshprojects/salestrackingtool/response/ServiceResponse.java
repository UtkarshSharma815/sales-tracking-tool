package com.utkarshprojects.salestrackingtool.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ServiceResponse {
    private Object response;
    private String message;
    private boolean success;
}
