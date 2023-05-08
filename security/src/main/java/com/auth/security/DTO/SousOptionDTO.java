package com.auth.security.DTO;

import com.auth.security.Entity.Option;
import com.auth.security.Entity.SousOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SousOptionDTO {
    private Integer id;
    private String name;





    public  SousOption mapToEntity(Option option){
        SousOption sousOption = new SousOption();
        sousOption.setId(getId());
        sousOption.setName(getName());
        //set id from relation
        sousOption.setOption(option);
        return sousOption;
    }

    public static SousOptionDTO mapToDto(SousOption sop) {
        SousOptionDTO dto = new SousOptionDTO();
        dto.setName(sop.getName());
        dto.setId(sop.getId());

        return dto;
    }
}
