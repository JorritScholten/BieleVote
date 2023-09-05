# BieleVote Development Documentation

## Ubiquitous Language Definition

| Word/phrase | Definition                                           |
|:-----------:|------------------------------------------------------|
|    user     | A person interacts with the system via the Frontend. |

## User types use case sketches

### Anonymous user

A user who does not have an account (yet), and is able to view the news page and current project page.

|  Word/phrase   | Definition                                                                                                                       |
|:--------------:|----------------------------------------------------------------------------------------------------------------------------------|
| Create account | Creating an account will make this user a citizen. To create an account a username, password, name and phone number is required. |
|   Read news    | This user is able to ONLY view the news page.                                                                                    |
| View projects  | This user is able to ONLY view the projects page.                                                                                |

### Citizen user

A user who can view, propose, comment and vote projects, view and react to the news.

|           Word/phrase           | Definition                                                                                                               |
|:-------------------------------:|--------------------------------------------------------------------------------------------------------------------------|
|        Log into account         | Logging in requires a correct username and password.                                                                     |
|            Read news            | The user is able to read news articles.                                                                                  |
|          React to news          | The user is able to like or dislike news articles.                                                                       |
|      Propose project ideas      | The user is able to propose a project idea after being active on X votings and is limited to X per month.                |
|    Comment on project ideas     | The user is able to comment on project ideas.                                                                            |
|     Vote for project ideas      | The user is able to vote on project ideas.                                                                               |
|           Get points            | The user is rewarded in points for voting, proposing successful project ideas and overall being an active member.        |
|    Trade points for rewards     | When the user has enough points gathered, they are able to purchase certain rewards in the point-shop                    |
| Be published on the leaderboard | The users who are rewarded the most points are published on a leaderboard, who also have the option to remain anonymous. |

### Municipal user

A user who can publish projects after reviewing, see the results of citizen voting, and publish news articles.

|      Word/phrase      | Definition                                                                       |
|:---------------------:|----------------------------------------------------------------------------------|
|   Log into account    | Logging in requires a correct username and password.                             |
|       Read news       | The user is able to read news articles.                                          |
|     Publish news      | The user is able to publish news articles.                                       |
|     React to news     | The user is able to like or dislike news articles.                               |
| Publish project ideas | The user is able to publish project ideas to the main voting page.               |
|    Review projects    | If the voting requirements are realized, the user is able to review the project. |
|  Reward shop orders   | The user is able to see the history of orders from the rewards shop.             |

### Administrator user

A user who has the power to create and delete user accounts. Publish items in the rewards shop and see the current
orders from the reward shop, and has an entrance to a dashboard of all the results of project ideas.

|      Word/phrase      | Definition                                                                       |
|:---------------------:|----------------------------------------------------------------------------------|
|   Log into account    | Logging in requires a correct username and password.                             |
|    Create accounts    | This user is able to create citizen user accounts and municipal user accounts.   |
|       Read news       | The user is able to read news articles.                                          |
|     Publish news      | The user is able to publish news articles.                                       |
|     React to news     | The user is able to like or dislike news articles.                               |
| Publish project ideas | The user is able to publish project ideas to the main voting page.               |
|    Review projects    | If the voting requirements are realized, the user is able to review the project. |
|   Result dashboard    | The user is able to see the results of previous projects.                        |
|  Reward shop orders   | The user is able to see the history of orders from the rewards shop.             |
|   Manage shop items   | The user is able to publish or delete reward shop items.                         |



## Frontend wireframes

## Project libraries

| Library name | Justification for use                     |
|:------------:|-------------------------------------------|
|      H2      | Postgres uses too much RAM to run on 8GB. |
