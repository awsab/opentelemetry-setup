package org.me.practise.parentproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class MDCInspectController {

    @GetMapping("/mdc-inspect")
    public String inspectMDC() {
        System.out.println("MDC contents: " + MDC.getCopyOfContextMap());
        return "Check console for MDC contents";
    }
}