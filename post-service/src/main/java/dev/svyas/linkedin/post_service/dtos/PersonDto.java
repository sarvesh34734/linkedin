package dev.svyas.linkedin.post_service.dtos;


import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class PersonDto {

    private Long id;

    private Long userId;

    private String name;

    private Set<PersonDto> connections;

}
