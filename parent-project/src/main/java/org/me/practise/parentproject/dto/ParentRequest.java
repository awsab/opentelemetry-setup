package org.me.practise.parentproject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ParentRequest {

    private String parentName;
    private String parentDescription;
    private String parentType;
    private String parentStatus;
    private String parentCreatedBy;
    private String parentCreatedDate;
    private String parentUpdatedBy;
    private String parentUpdatedDate;
    private String parentVersion;
}
