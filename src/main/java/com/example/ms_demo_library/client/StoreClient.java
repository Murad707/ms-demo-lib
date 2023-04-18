package com.example.ms_demo_library.client;

import com.example.ms_demo_library.model.client.ClientStoreDtoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-library-store",url ="${client.ms-library-store.endpoint}" )
public interface StoreClient {
    @GetMapping("/v1/stocks")
     List<ClientStoreDtoRequest> getStocks(@RequestParam Double rating);
}
