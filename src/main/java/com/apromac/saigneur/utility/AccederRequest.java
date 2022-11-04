package com.apromac.saigneur.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class AccederRequest {
    private Long profilID;
    private LinkedList<Long> menuIDs;
}
