package com.minorr.backuptoolbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.minorr.backuptoolbackend.web.model.BasicResponse;
import com.minorr.backuptoolbackend.core.util.FileSystemInfo;
import com.minorr.backuptoolbackend.web.config.MessageTemplates;
@RestController
@RequestMapping("/snapshots")
public class SnapshotRestController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BasicResponse getAll(){
        FileSystemInfo fsi = new FileSystemInfo();
        BasicResponse response = new BasicResponse(MessageTemplates.SUCCESS_STATUS,MessageTemplates.NO_MSG, fsi.getDisksInfo());

        return response;
    }
}
