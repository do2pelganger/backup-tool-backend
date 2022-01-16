package com.minorr.backuptoolbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.minorr.backuptoolbackend.web.model.BasicResponse;

@RestController
@RequestMapping("/snapshots")
public class SnapshotRestController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse getAll(){
        ArrayList<String> list = new ArrayList<String>();

        list.add("test1");
        list.add("test2");
        list.add("test3");

        BasicResponse response = new BasicResponse("success","no messsage", list);

        return response;
    }
}
