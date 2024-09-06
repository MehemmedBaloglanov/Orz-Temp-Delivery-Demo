package com.intelliacademy.orizonroute.valueobjects.common;

import com.intelliacademy.orizonroute.valueobjects.user.RoleName;

@ValueObject
public sealed class Role permits Role.Anonymus {
    private final RoleName name;
    private final String description;
    private Role(RoleName name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Role of(RoleName value, String description) {
        return new Role(value, description);
    }

    public static Role empty() {
        return new Anonymus();
    }

    public RoleName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isNil() {
        return this instanceof Anonymus;
    }

    public Role withName(RoleName name) {
        return new Role(name, this.description);
    }

    public Role withDescription(String description) {
        return new Role(this.name, description);
    }

    public static final class Anonymus extends Role {
        public Anonymus() {
            super(RoleName.ANONYMOUS, "");
        }
    }


    public static class Prototype {
        public static final Role ADMIN = Role.of(RoleName.ROLE_ADMIN, "Admin");
        public static final Role COMPANY = Role.of(RoleName.ROLE_COMPANY, "Company");
        public static final Role CUSTOMER = Role.of(RoleName.ROLE_CUSTOMER, "Customer");
        public static final Role COURIER = Role.of(RoleName.ROLE_COURIER, "Courier");
        public static final Role ROBOT = Role.of(RoleName.ROLE_ROBOT, "Robot");
    }
}
