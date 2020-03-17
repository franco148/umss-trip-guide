package com.umss.projects.common.dto.request;

import com.umss.projects.domain.RoleEnum;

public class RoleRegistrationDto {

	private RoleEnum authority;


    public RoleEnum getAuthority() {
        return authority;
    }

    public void setAuthority(RoleEnum authority) {
        this.authority = authority;
    }
}
