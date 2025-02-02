package com.utkarshprojects.salestrackingtool.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mustafa Mubashir
 * @version 1.5
 * @since 14-07-2023
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ServiceResponse {
    private Object response;
    private String message;
    private boolean success;
}
