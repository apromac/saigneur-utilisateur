package com.apromac.saigneur.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "saigneur-utilitaire", url = "${apromac.msaigneur-utilitaire.url}")
public interface MicroserviceUtilitaireProxy {

}
