module vicuna.infra.jakarta {
    exports com.scprojekt.infrastructure.repositories;

    requires vicuna.core.lib;
    requires jakarta.persistence;
    requires java.transaction;
    requires lombok;
    requires jakarta.cdi;
    requires jakarta.transaction;
}