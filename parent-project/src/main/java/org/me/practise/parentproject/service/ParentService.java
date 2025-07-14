package org.me.practise.parentproject.service;

import lombok.RequiredArgsConstructor;
import org.me.practise.parentproject.client.PersonServiceClient;
import org.me.practise.parentproject.dto.ParentRequest;
import org.me.practise.parentproject.dto.ParentResponse;
import org.me.practise.parentproject.entity.Parent;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final PersonServiceClient personServiceClient;

    private static final List<Parent> parents = new ArrayList<> ( List.of(
        new Parent(1, "Parent1", "Description1", "Type1", "Active", "User1", "2023-01-01", "User1", "2023-01-02", "v1"),
        new Parent(2, "Parent2", "Description2", "Type2", "Inactive", "User2", "2023-01-03", "User2", "2023-01-04", "v1"),
        new Parent(3, "Parent3", "Description3", "Type3", "Active", "User3", "2023-01-05", "User3", "2023-01-06", "v1"),
        new Parent(4, "Parent4", "Description4", "Type4", "Inactive", "User4", "2023-01-07", "User4", "2023-01-08", "v1"),
        new Parent(5, "Parent5", "Description5", "Type5", "Active", "User5", "2023-01-09", "User5", "2023-01-10", "v1"),
        new Parent(6, "Parent6", "Description6", "Type6", "Inactive", "User6", "2023-01-11", "User6", "2023-01-12", "v1"),
        new Parent(7, "Parent7", "Description7", "Type7", "Active", "User7", "2023-01-13", "User7", "2023-01-14", "v1"),
        new Parent(8, "Parent8", "Description8", "Type8", "Inactive", "User8", "2023-01-15", "User8", "2023-01-16", "v1"),
        new Parent(9, "Parent9", "Description9", "Type9", "Active", "User9", "2023-01-17", "User9", "2023-01-18", "v1"),
        new Parent(10, "Parent10", "Description10", "Type10", "Inactive", "User10", "2023-01-19", "User10", "2023-01-20", "v1")
    ));


    public ResponseEntity<ParentResponse> createParent(Parent parent) {
        parents.add(parent);

        ParentResponse response = ParentResponse.builder()
                .parentId(parent.getParentId())
                .parentName(parent.getParentName())
                .parentDescription(parent.getParentDescription())
                .parentType(parent.getParentType())
                .parentStatus(parent.getParentStatus())
                .parentCreatedBy(parent.getParentCreatedBy())
                .parentCreatedDate(parent.getParentCreatedDate())
                .parentUpdatedBy(parent.getParentUpdatedBy())
                .parentUpdatedDate(parent.getParentUpdatedDate())
                .parentVersion(parent.getParentVersion())
                .build();

        return ResponseEntity.ok(response);
    }


    public Parent getParentById(long parentId) {

        System.out.println("MDC contents ******: " + MDC.getCopyOfContextMap());
        System.out.println("traceId: " + MDC.get("traceId"));
        System.out.println("spanId: " + MDC.get("spanId"));
        // Simulating a call to an external service to fetch person details
        System.out.println (personServiceClient.getPersonByPersonId (1L));

        return parents.stream()
                .filter(parent -> parent.getParentId() == parentId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + parentId));
    }


    public ResponseEntity<List<ParentResponse>> getAllParents() {
        List<ParentResponse> responseList = new ArrayList<>();
        for (Parent parent : parents) {
            ParentResponse response = ParentResponse.builder()
                    .parentId(parent.getParentId())
                    .parentName(parent.getParentName())
                    .parentDescription(parent.getParentDescription())
                    .parentType(parent.getParentType())
                    .parentStatus(parent.getParentStatus())
                    .parentCreatedBy(parent.getParentCreatedBy())
                    .parentCreatedDate(parent.getParentCreatedDate())
                    .parentUpdatedBy(parent.getParentUpdatedBy())
                    .parentUpdatedDate(parent.getParentUpdatedDate())
                    .parentVersion(parent.getParentVersion())
                    .build();
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }
}
