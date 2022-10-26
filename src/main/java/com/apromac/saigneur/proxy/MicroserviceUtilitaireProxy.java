package com.apromac.saigneur.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "saigneur-utilitaire", url = "${apromac.msaigneur-utilitaire.url}")
public interface MicroserviceUtilitaireProxy {

}




//
//
//
//    @GetMapping("/api/v1/zone/findByZoneID/{zoneID}")
//    ZoneBean recupererUneZone(@PathVariable long zoneID);
//
//    @GetMapping("/api/v1/zone/findByLibelleZone/{libelleZone}")
//    ZoneBean recupererUneZone(@PathVariable String libelleZone);
//
//    @GetMapping("/api/v1/district/findAllDistrict")
//    ResponseEntity<DistrictBean> recupererDistricts();
//
//    @GetMapping(value = "/api/v1/zone/findByDistrict/{districtID}")
//    public ResponseEntity<List<ZoneBean>> recupererZoneParDistrict(@PathVariable long districtID);