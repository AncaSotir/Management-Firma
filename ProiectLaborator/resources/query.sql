CREATE database mycompany;
use mycompany;

CREATE TABLE jobs (
    job_id int(11) NOT NULL AUTO_INCREMENT,
    title varchar(20) NOT NULL,
    min_salary int(10) DEFAULT NULL,
    max_salary int(10) DEFAULT NULL,
    PRIMARY KEY (job_id)
);

CREATE TABLE departments (
    department_id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    manager_id int(11) DEFAULT NULL,
    PRIMARY KEY (department_id)
);

CREATE TABLE employees (
    employee_id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    date_of_birth date DEFAULT NULL,
    email varchar(45) DEFAULT NULL,
    phone_number varchar(11) DEFAULT NULL,
    hire_date date DEFAULT NULL,
    job_id int(11) DEFAULT NULL,
    department_id int(11) DEFAULT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id) ON DELETE SET NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id) ON DELETE SET NULL
);


ALTER TABLE departments ADD FOREIGN KEY (manager_id) REFERENCES employees(employee_id) ON DELETE SET NULL;


CREATE TABLE projects (
    project_id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    persons_required int(3) DEFAULT NULL,
    PRIMARY KEY (project_id)
);

CREATE TABLE teams (
    team_id int(11) NOT NULL AUTO_INCREMENT,
    project_id int(11) DEFAULT NULL,
    employee_id int(11) DEFAULT NULL,
    PRIMARY KEY (team_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE SET NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE SET NULL
);

CREATE TABLE tasks (
    task_id int(11) NOT NULL AUTO_INCREMENT,
    description varchar(45) NOT NULL,
    project_id int(11) DEFAULT NULL,
    PRIMARY KEY (task_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE SET NULL
);

