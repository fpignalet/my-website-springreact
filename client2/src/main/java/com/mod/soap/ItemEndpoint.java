package com.mod.soap;

import com.mod.xml.item.ItemRequest;
import com.mod.xml.item.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://localhost:8081/mod/xml/item">
 <soapenv:Header/>
 <soapenv:Body>
 <sch:ItemRequest>
 <sch:field1>Field1</sch:field1>
 </sch:ItemRequest>
 </soapenv:Body>
 </soapenv:Envelope>
 */
@Endpoint
public class ItemEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081/mod/xml/item";

    @Autowired
    private ItemRepository itemRepo;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ItemRequest")
    @ResponsePayload
    public ItemResponse getItem(final @RequestPayload ItemRequest request) {
        final ItemResponse response = new ItemResponse();
        response.setItem(itemRepo.findItem(request.getName()));
        return response;
    }
}
