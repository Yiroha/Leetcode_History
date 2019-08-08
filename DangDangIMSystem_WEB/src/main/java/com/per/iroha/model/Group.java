package com.per.iroha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    private String groupName;

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", userList=" + userList +
                '}';
    }

    private Set<Integer> userList;

}
