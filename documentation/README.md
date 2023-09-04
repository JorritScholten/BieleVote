# BieleVote Development Documentation

## Ubiquitous Language Definition

|     Word/phrase     | Definition                                                                                                |
|:-------------------:|-----------------------------------------------------------------------------------------------------------|
|        user         | A person who interacts with the system via the Frontend, there are various types.                         |
|       account       | All users (except an anonymous visitor) get an account to identify them, comes in various flavours.       |
|   anonymous user    | A user without an account or someone who hasn't logged in yet.                                            |
| administrative user |                                                                                                           |
|   municipal user    |                                                                                                           |
|    citizen user     |                                                                                                           |
|       entity        | A Java object that defines a storage table in the database attached to the backend.                       |
|      REST API       | The HTTP endpoints made available by the backend to the frontend for data transfer and functionality.     |
|        token        | A string of characters used by the frontend to gain temporary access to secured parts of the backend API. |

## User types use case sketches

### anonymous user

### municipal user

### citizen user

### administrator user

## Frontend wireframes

## Project libraries

|   Library name   | Justification for use                                                     |
|:----------------:|---------------------------------------------------------------------------|
|        H2        | Postgres uses too much RAM to run on a system with only 8GB.              |
| Spring REST Docs | Seems like it might be useful, if it doesn't take too much effort to use. |
| Tailwind+PostCSS | There was already experience with this styling method for the frontend.   |
|   Semantic-UI    | It makes the REACT code of the frontend more legible.                     |
|      Axios       | Simplifies API interactions for the REACT frontend.                       |
|   react-icons    | Clean looking icons with minimal fuss.                                    |
