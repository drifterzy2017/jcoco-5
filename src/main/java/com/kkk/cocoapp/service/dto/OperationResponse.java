package com.kkk.cocoapp.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 13714 on 2019/1/3.
 */

@Data //for getters and setters
public class OperationResponse {
    public enum ResponseStatusEnum {SUCCESS, ERROR, WARNING, NO_ACCESS}

    @ApiModelProperty(required = true)
    private ResponseStatusEnum  operationStatus;
    private String  operationMessage;
}
