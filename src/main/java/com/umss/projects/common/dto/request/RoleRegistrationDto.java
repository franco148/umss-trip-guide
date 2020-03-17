package com.umss.projects.common.dto.request;

import javax.validation.constraints.NotNull;

import com.umss.projects.persistence.domain.RoleEnum;

public class RoleRegistrationDto {

	@NotNull
	private RoleEnum authority;


    public RoleEnum getAuthority() {
        return authority;
    }

    public void setAuthority(RoleEnum authority) {
        this.authority = authority;
    }
}
