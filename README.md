# RailwayTicketBooking
A Spring Boot CRUD API to handle client-side requests for Railway Ticket Booking related operations.

With the help of GraphQL a single endpoint namely '/api' is exposed, from which various query and mutation methods are written as business logic under a service layer.
Various entities are written to hold the data from database with the help of hibernate.
JWT authentication is done in a separate REST API name '/login'.

The schemas for the graphQL query and mutation is included under the resource folder.
