package org.me.practise.parentproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.me.practise.parentproject.dto.ParentResponse;
import org.me.practise.parentproject.entity.Parent;
import org.me.practise.parentproject.service.ParentService;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
@Slf4j
public class ParentController {

    private final ParentService parentService;

    @GetMapping("/{parentId}")
    public Parent getParentById(@PathVariable long parentId) {
        return parentService.getParentById(parentId);
    }

    @PostMapping()
    public ResponseEntity<ParentResponse> createParent(@RequestBody Parent parent) {
        System.out.println("MDC contents: " + MDC.getCopyOfContextMap());
     return parentService.createParent(parent);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParentResponse>> getAllParents() {
        return parentService.getAllParents();
    }
}
