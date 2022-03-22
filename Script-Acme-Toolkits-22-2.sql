drop database if exists `Acme-Toolkits-22.2`;
create database `Acme-Toolkits-22.2`;

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Toolkits-22.2`.* to 'acme-user'@'%';
