<p align="center">
<img width="200" height="100" src="https://4.bp.blogspot.com/-oDAbn7pY67o/WrwYZVEVj8I/AAAAAAAAMFY/hV9iHsA0K7UTMVZvLI-xe072mEkzQrpXACLcBGAs/s1600/logo%2Bsantander%2Bnuevo2.jpg">
</p>

# Final Project: TechHotel

<p align="center"><strong>Ana Martins Santos</strong></p>

## Index
* [1. Goal](#goal)
* [2. Introduction](#introduction)
* [3. Tools](#tools)
* [4. Microservices Structure](#microservices-structure)
  * [4.1. Deploy](#deploy)
* [5. Documentation](#documentation)
  * [5.1. JavaDoc By Microservice](#javadoc-by-microservice)
  * [5.2. Swagger](#swagger)
* [6. Test Coverage](#test-coverage)
* [7. Front End](#front-end)
  * [7.1. New Framework Implementation](#new-framework-implementation)
  * [7.2. Methodology](#methodology)
  * [7.3. Credentials](#credentials)
 
<p align="center"><b>NOTE:</b> The Front-End is located on the following repository Git: https://github.com/almsasantos/TechHotel-Angular</p>

## Goal
The goal of this project was to create a **Spring Boot Back-End** and an **Angular Front-End** application under the requirements available [here](https://my.ironhack.com/lms/courses/course-v1:IRONHACK+JAVA_MASTER+2020_v1/units/18f40367269c4e5b8b6c92175fe90e5a), for that matter **TechHotel** was bornt. The purpose of TechHotel is to allow users to register and make different types of reservations in the hotel, via web.

## Introduction
TechHotel is an application that allows the registration of two different types of users (**Basic User** and **Premium User**) by entering their full name, unique username, password, phone number, email, address and account (to keep track of their possible future reservations). 

In this hotel, there are a total of **20 rooms** available, these rooms are divided in two categories **15 Regular Rooms** and **5 Suite Rooms**, so once the user is registed and logged into the app they are allowed to **make a room reservation**. Each room has its own features even though the most important one is the price, a **Basic User has to pay 20% more** for a **Suite Room** in comparison with the Premium User.

Once the user has a room, they are allowed to make appointments in different kinds of hotel services: **Massages**, **Pool Rents** and **Room Food Services**. Every service has an associated price with it. There are in total **5** different **type of massages** available, in terms of pool rents the user can rent "x" number of **floaties** and **towels** and when requesting for a room food service there are a **Food Menu** and a **Drink Menu** with a bunch of options available and different prices. After requesting for a service, an **invoice** of that **specific service** will be **created** with the user id and its room where they are located, the total price of the service and the type of service provided.

Each service can be updated if it hasn't been provided yet, for example the number of Towels or Floaties rented in a Pool Rent can be changed if the hour of the rent hasn't passed yet the time we are in, same goes for the massages. In the case of Room Food Services, the user is allowed to update their Food Menu and/or Drink Menu if the food hasn't been delivered it. Every time an update occurs a new invoice will be emitted. **All services requests only occurs when user has enough balance for them**.

A side note is that **Swagger Documentation** is available for the **local microservices** in your computer, on the other hand the **Postman Documentation** is available for the **microservices deployed** in **cloud**.

## Tools
- IntelliJ (Compile and run Java Program, JDK 11).
- Spring Boot Dependencies.
- MySQL.
- MongoDB.
- Postman.
- Swagger (API Document with HTML).
- Drawio (Draw User Case Diagram and Class Diagram).
- Angular (including HTML, CSS and TypeScript) to create the front-end of the app.
- **New Framework**: OpenStreetMap Library.
- Heroku to deploy the app microservices in Cloud.


## Microservices Structure

<p align="center"><img width="700" height="450" src="https://i.ibb.co/MCZKgGj/Untitled-Diagram.png"></p>

The application is built with **8** different microservices:
- **Eureka Server**: In which relies the whole application.
- **Edge Service**: The user interface communicates directly with this microservice which distributes the respective functionality to the other microservices.
- **Security Service**: Is the **first microservice that Edge Service communicates to**, and establish Security into the application allowing the right users to the right functionality.
- **User Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the different type of users (Admin, Basic User and Premium User).
- **Room Service**: Is associated with a **MySql database** and responsible to make the CRUD (Create-read-update-delete) requests about the different type of rooms (Regular Rooms and Suites).
- **Reservation Service**: Also associated with a **MySql database**, allows Basic Users and Premium Users to make a room reservation (in a Regular Room or Suite Room) of their choice, if the room is available.
- **Activity Service**: Is associated with a **MySql database** as well, allows both type of users once having a room reservation to make appointments for massages, make pool rents of floaties and towels and request for a room food service to choose from two menus available, a food menu and a drink one.
- **Invoice Service**: Is associated with a **Mongo database**, this microservice is responsible for storing all invoices made with the different types of services provided (Room Reservation, Massages, Pool Rents, Room Food Services and End of Hotel Stay).

Once in the application, the first thing users will come accross is the **Landing page**. The rest of the application is only available for logged in users. On that note, in order to establish security into the application web a **Login form** was created and the **CookieService** module was used. The **CookieService** was also used to store, in a separate cookie, the username so that way all requests can be made automatically by passing that same cookie value, instead of making the user write their user id and room id, the cookie stores all in it.

### Deploy

All application Microservices were **deployed in cloud**, using **Heroku**. Down bellow, there are all links in which the microservices are lifted.

* [Eureka Server](https://techhotel-eureka.herokuapp.com/)
* [Edge Service](https://techhotel-edge.herokuapp.com/)
* [Security Service](https://techhotel-security.herokuapp.com/)
* [User Service](https://techhotel-user.herokuapp.com/)
* [Room Service](https://techhotel-room.herokuapp.com/)
* [Reservation Service](https://techhotel-reservation.herokuapp.com/)
* [Activity Service](https://techhotel-activity.herokuapp.com/)
* [Invoice Service](https://techhotel-invoice.herokuapp.com/)

<p align="center"><img width="1000" height="500" src="https://i.ibb.co/tQM9wJB/Screenshot-from-2020-07-23-22-55-39.png"></p>

## Documentation

### JavaDoc by Microservice
Go through the following links to checkout JavaDoc Documentation for each Microservice:
* [Eureka Server](./documentation/eureka-service)
* [Edge Service](./documentation/edge-service)
* [Security Service](./documentation/security-service)
* [User Service](./documentation/user-service)
* [Room Service](./documentation/room-service)
* [Reservation Service](./documentation/reservation-service)
* [Activity Service](./documentation/activity-service)
* [Invoice Service](./documentation/invoice-service)


### Swagger
The **Swagger documentation** is available for the microservices in your local computer, to access it you must enter the following link: http://localhost:8081/swagger-ui.html

You'll find the page bellow where you can make requests from all the application.

<p align="center"><img width="1000" height="600" src="https://i.ibb.co/sqtfQgV/Screenshot-from-2020-07-23-08-33-39.png"></p>

### Postman
The **Postman documentation** is available with the url of the **microservices deployed in cloud**. Do to the hibernate of the microservices once deployed in heroku, at first it might take a few request to actually wake the microservice up and make it work.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/974b0253af2371803796)

## Test Coverage

| Microservice | Class % | Method % | Line % |
| ------ | --------------- | --------------- | --------------- |
| Eureka Server   | 100 % | 100 % | 100 % |
| Room Service   | 100 % | 100 % | 100 % |
| User Service   | 100 % | 94 % | 96 % |
| Security Service   | 100 % | 84 % | 91 % |
| Invoice Service   | 100 % | 97 % | 84 % |
| Reservation Service   | 100 % | 99 % | 85 % |
| Activity Service   | 100 % | 97 % | 81 % |
| Edge Service   | 100 % | 88 % | 81 % |

## Front-End
<p align="center"><b>NOTE:</b> THE FRONT-END IS LOCATED ON THE FOLLOWING REPOSITORY GIT: https://github.com/almsasantos/TechHotel-Angular</p>

The front-end is only dedicated to the **Admin** and **Basic** Users, to have to access to all functionality you must use **Swagger** in your computer.

This application contains an **admin view** and a **user view** depending on each user logs in.

<p align="center"><img width="1000" height="490" src="https://i.ibb.co/s2CntrK/Screenshot-from-2020-07-22-21-13-08.png"></p>

### New Framework Implementation
To fullfill the last requirement of this project, the **OpenStreetMap library** was used. This framework allowed the display of the TechHotel's location in a map with a marker pointing at its precise geography position.
<p align="center"><img width="1000" height="490" src="https://i.ibb.co/qWxdhHQ/Screenshot-from-2020-07-23-10-34-59.png"></p>

### Methodology
The front-end application was built with **10** most important pages: 
* **Landing page**: Is the first page which users will come across and to have any access to the rest of the application must login.
* **Login**: Allow **admin** and **basic users** to login by passing the correct credentials (**username** and **password**).
* **Registration**: Allow clients to register into the application by providing a few personal requirements.
* **Home**: Welcomes users once they're logged in and displays all services available to them.
* **Room Reservation**: Page only available to **Basic Users** and allows them to make a room reservation, in a **Regular Room** or in a **Suite Room**.
* **Massages**: Available for users and admins, this page allows users to make massages appointments and displays on a table all massage appointments for users and admins.
* **Pool Rents**: Available for users and admins, this page allows users to make pool rents and displays on a table all pool rents made for users and admins.
* **Room Food Services**: Available for users and admins, this page allows users to make room food requests and displays on a table all room food requests made for users and admins.
* **Profile**: Page **only available** to **Basic Users**, where it's displayed all their personal information.
* **Invoices**: Page **only available** to **Admins**. Displays on a table all invoices from every Hotel Service (Room Reservation, Massages, Pool Rents, Room Food Services and End of Stay) provided.
* **Users**: Page **only available** to **Admins**. Displays on a table all non-to-personal information about Basic Users and Premium Users. Allows Admins to block any type of user.
* **Rooms**: Page **only available** to **Admins**. Displays on a table all information about Regular Rooms and Suite Rooms, their features and their availability at the moment.


### Credentials
The first thing to do is to **create an Admin**, for that you can use Postman (https://techhotel-edge.herokuapp.com/users/admins) or Swagger (http://localhost:8081/users/admins)

| Username | Password |
| ------ | --------------- |
| admin   | admin |

To enter the application as an official **user** you must register first and then **login** with your **username** and matching **password**.

<p align="center"><img width="400" height="250" src="https://i.ibb.co/8jb2bK1/logito-removebg-preview.png"></p>

<p align="center"><strong>Welcome to TechHotel!</strong></p>

