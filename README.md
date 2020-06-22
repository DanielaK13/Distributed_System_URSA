# URSA

This project is a **Distributed System** for the "*Universidade Regionalizada Salvador de América* (URSA)".

This project allows **sending job opportunities, internships and scholarships** by rural producers and companies.

Several applications with a communication interface were created to publicize opportunities, such as:

- Opportunity receiver that communicates through a **Socket UDP**, port 2001;
- Database access server that accepts **TCP** connections, port 1972;
- **SOAP** Web Services;
- **Rest** Web Services;
- Android **App**;
- Integration with **Twitter**.

## Database

The database is used for storing job opportunities, internship and scholarship data.

The code manages opportunities as follows:
- when they arrived;
- which ones were attended to;
- what types are the most searched.

The database has the following layout:

### Opportunity
```
\#codigo: Integer (primary key)
@codcargo: Integer (foreign key cargo)
descricao: varchar(4000)
acesso: Integer (encoded)
ingresso: Timestamp
fechada: Timestamp
```

#### Input:

The field ```acesso``` serves to indicate to students which courses the opportunity is valid for. URSA uses the following accesses:

- 1 – *Somente Agronomia* (only Agronomy)
- 2 – *Somente Engenharia Agronômica* (only Agronomic Engineering)
- 3 – *Somente Agronogócio* (only Agribusiness)
- 4 – *Agronomia ou Eng. Agronômica* (Agronomy or Agronomic Engineering)
- 5 – *Agronomia ou Agronegócio* (Agronomy or Agribusiness)
- 6 – *Eng. Agronômica ou Agronegócio* (Agronomic Engineering or Agribusiness)
- 7 – *Todos os cursos* (all courses)

### Post/job
```
\#codcargo: Integer (primary key)
descricao: varchar (100)
tipo: Integer
```

#### Input

The field ```tipo``` serves to indicate the type of opportunity for that position. The following types used in URSA are:

- 1 – *Emprego formal* (formal employment)
- 2 – *Estágio até 20h/semana* (internship up to 20h/week)
- 3 – *Estágio acima de 20h/semana* (internship above 20h/week)
- 4 – *Estágio voluntário* (voluntary internship)
- 5 – *Bolsa de pesquisa* (research scholarship)
- 6 – *Bolsa de extensão* (extension scholarship)
- 7 – *Bolsa de graduação* (graduation scholarship)

## Sockets UPD and TCP

O servidor/serviços suportam as seguintes operações:

- **Adiciona**: adds an opportunity to the database;
- **Altera**: changes an opportunity to database;
- **Excluir**: deletes an opportunity from database; 
- **Consulta**: returns data for an opportunity;
- **ListaOportunidades**: receives the post/job code and returns a list containing the opportunities for that post/job;
- **ListaAbertas**: receives the type of opportunity (or no type, indicating all types) and return the open opportunities for that type.

**Observations**:

When servers/services receive an opportunity inclusion request, they automatically fill in field ```ingresso``` with date and time the request was received at server/service.

The server that communicates with a UDP socket, only supports messages of up to 100 characters and doesn't support the operations *ListaOportunidades* and *ListaAbertas*.

The server that communicates via TCP has a user interface in text mode, allowing URSA employees access to interact with opportunities, based on the planned operations. In addition, both it and the server that communicates with a TCP socket build a processing log of operations performed.


## Web Services:

Web services provide access to the same operations as TCP server. In case of Rest services, operations are implemented in four HTTP methods: GET, PUT, POST and DELETE. The data encoding is **XML** in SOAP and **Json** in Rest.

The project contain the following clients:

- **Client with command line interface for UDP server**: the application receives the arguments in command line, sends the request message to server, waits for response and shows to the user. The available options are the same as those available on server;
- **Client with graphical interface for interaction with SOAP Web Service**: provides access to all operations made available by the SOAP Service;
- **Web client for interaction with the Rest Web Service**: the application provides access to all operations provided by the service with a web interface.

## URSA Story

The *Universidade Regionalizada Salvador da América* (URSA) is one of the largest universities in its region. The region covered by URSA comprises an essentially agricultural area, with many small, large and medium-sized agricultural producers. Even though it is agricultural, the region is very well served by information and communication technology services, with great connectivity and indiscriminate access by residents and businessmen to computing devices of various types. 

URSA offers several undergraduate courses to residents of the region, including Agronomy, Agronomic Engineering and Agribusiness (CST) courses. Producers and companies in the agricultural area are constantly in contact with URSA coordinators to request nominations or advertise opportunities: jobs, internships or scholarships (research, extension or development). There are so many ways in which opportunities are available that URSA coordinators and employees are having trouble managing and disseminating these opportunities.

#### This was an assignment for Distributed Systems class by Ph.D Marcelo Trindade Rebonatto at Passo Fundo University (2017-2).
