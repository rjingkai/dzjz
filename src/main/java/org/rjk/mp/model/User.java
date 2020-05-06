package org.rjk.mp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long userid;
    private String name;
    private String password;
    private String username;
}
