package com.apromac.saigneur.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccederRequest {
    private Long profilID;
    private List<Long> menuIDs;
}
