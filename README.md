## Apartment Shammer

### Final Group Project for Skill Distillery

### Team: Dapper Facade
* Chana Cohn
* Dunwei Zheng
* Ryan Harrington
* Kayla Hatle

### Overview
Aparment Shamer is an application that allows users to browse apartments and complaints that residents have logged. Users can register and log their own complaints, update and resolve them.
Google location and autocomplete services are included, and users can also add a new complex.

### How to use
Users can search on a city or address to view complexes and their complaints. If a use is logged in, they can search or add a complex and add complaints, update or delete them. An administrator can disable a user and delete content. A navbar provides appropriate options based on authentication status and role.

### REST API Endpoints
| HTTP Verb | Resource URI                        | Action                               |
|-----------|-------------------------------------|--------------------------------------|
|                                                                                        |
|Complex    |                                     |                                      |
| GET       | /api/complexes/                     | Retrieve All complexes               |
| POST      | /api/complexes/                     | Add a complex                        |
| PUT       | /api/complexes/{id}                 | Update one complex                   |
| GET       | /api/complexes/{name}               | Retrieve complex by search           |
| GET       | /api/complexes/{id}/complaints      | Retrieve all Complaints for a complex|
| GET       | /api/complexes/{id}/complaints/{cid}| Retrieve one complaint from a complex|
| POST      | /api/complexes/{id}/complaints      | Add a complaint for a complex        |
| PUT       | /api/complexes/{id}/complaints/{cid}| Update a complaint                   |
| DELETE    | /api/complexes/{id}/complaints/{id} | Delete one complaint                 |
|                                                                                        |
|Complaints |                                     |                                      |
| GET       | /api/complaints/{id}/images         | Retrieve all images                  |
| GET       | /api/complaints/{id}/images{mid}    | Retrieve one image                   |
| DELETE    | /api/complaints/{id}/images/{id}    | Delete one image                     |
| GET       | /api/complaints/{id}/comments       | Retrieve all comments                |
| GET       | /api/complaints/comments/{cid}      | Retrieve one comment                 |
| POST      | /api/complaints/{id}/comments       | Add a complaint                      |
| PUT       | /api/complaints/{id}/comments/{cid} | Retrieve all comments                |
| DELETE    | /api/complaints/{id}/comments/{cid} | Delete one comment                   |
|                                                                                        |
|Contacts   |                                     |                                      |
| GET       | /api/contacts                       | Retrieve all contacts                |


### Technologies Used
* Spring MVC
* mySql Workbench
* Atom
* Java
* Angular
* GitHub
* CSS
* HTML

### Lessons Learned

* Adopting a lot of new technologies in a week was a stretch! We had to make judgment calls about whether to cut a feature or press through to include it.
* Google integration took quite a bit of time. It was important to us, so we persevered to make it work.
* When using Angular on the front end, it was important to use compatible front end templates and tools. Some of the front end did not look as graphically interesting as we would have liked because adding pictures and features kept breaking the navbar. This is something to come back to.
