package org.me.practise.parentproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parent {

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
