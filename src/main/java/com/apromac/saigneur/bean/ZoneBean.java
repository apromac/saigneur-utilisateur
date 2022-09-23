package com.apromac.saigneur.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZoneBean {
    private Long zoneID;
    private String libelleZone;
    private DistrictBean district;
}
