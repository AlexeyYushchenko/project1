package utlc.ru.project1.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
    ADMIN,
    USER,
    OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}