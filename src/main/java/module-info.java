module vicuna.infra.jakarta {
    exports com.scprojekt.infrastructure.repositories;
    requires vicuna.core.lib;
    requires java.persistence;
    requires lombok;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires jakarta.transaction;
}