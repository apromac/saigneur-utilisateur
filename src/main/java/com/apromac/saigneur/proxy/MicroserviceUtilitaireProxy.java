package com.apromac.saigneur.proxy;

import com.apromac.saigneur.bean.DistrictBean;
import com.apromac.saigneur.bean.ZoneBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "saigneur-utilitaire", url = "${apromac.msaigneur-utilitaire.url")
public interface MicroserviceUtilitaireProxy {

    @GetMapping("/api/v1/district/findAllDistrict")
    ResponseEntity<DistrictBean> recupererDistricts();

    @GetMapping("/zone/findByZoneID/{zoneID}")
    ZoneBean recupererUneZone(@PathVariable long zoneID);

    @GetMapping(value = "/api/v1/zone/findByDistrict/{districtID}")
    public ResponseEntity<List<ZoneBean>> recupererZoneParDistrict(@PathVariable long districtID);

}
