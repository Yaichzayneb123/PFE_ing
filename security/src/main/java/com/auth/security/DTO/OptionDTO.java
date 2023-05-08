package com.auth.security.DTO;

import com.auth.security.Entity.Option;
import com.auth.security.Entity.SousOption;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OptionDTO {
    private Integer id;
    private String name;
    private List<SousOptionDTO> sousOptionsList;



}
