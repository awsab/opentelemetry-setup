package org.me.practise.parentproject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ParentResponse {
    private long parentId;
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
