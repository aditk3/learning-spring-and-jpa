package com.aditkapoor.hospitalManagement.dto;

import com.aditkapoor.hospitalManagement.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupCountResponseDto {

    private BloodGroupType bloodGroupType;

    private Long count;
}
