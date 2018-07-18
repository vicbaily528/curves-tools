package com.curves.tool.services.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * @author li.xiangdong
 */
@Data
public class DuridDTO {

    private String druidName;
    private String druidPass;
    private String allowAddr;
    private String denyAddr;
}
