drop database if exists `Acme-Toolkits-22.3`;
create database `Acme-Toolkits-22.3`;

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Toolkits-22.3`.* to 'acme-user'@'%';
