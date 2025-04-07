# discord-oauth2-api
## A FORK FROM THE [ORIGINAL](https://github.com/Mokulu/discord-oauth2-api)!!!


A small Discord OAuth2 API wrapper for Java.


## Features
* Generates the authorization URL.
* Code authorization for access token and refresh token.
* Refresh the access token with refresh token.
* Get the user, guilds, and connection info of a user from access token.


## ALL IRREVERENT!!!

## Using the API
### Creating the OAuth handler

```java
import xyz.rugman27.discord.oauth.DiscordOAuth;

DiscordOAuth oauthHandler = new DiscordOAuth(clientID:String,clientSecret:String,redirectUri:String,scope:String[]);
```

#### Generating the OAuth URL
```java
String authURL = oauthHandler.getAuthorizationURL(state: String);
```
`state` will be ignored by passing null.

#### Authorizing the `code`

```java
import model.xyz.rugman27.discord.oauth.TokensResponse;

TokensResponse tokens = oauthHandler.getTokens(code:String);
String accessToken = tokens.getAccessToken();
String refreshToken = tokens.getRefreshToken();
```

#### Refreshing the Access Token
```java
TokensResponse tokens = oauthHandler.refreshTokens(refresh_token: String);
```

### Creating the API handler

```java
import xyz.rugman27.discord.oauth.DiscordAPI;

DiscordAPI api = new DiscordAPI(access_token:String);
```

The following API fetch calls will throw `IOException (HttpStatusException)` when access is denied due to invalid scope or expired token.

#### Fetching User
Scope `identify` is required.
Scope `email` is required for `email` and `verified` values.

```java
import model.xyz.rugman27.discord.oauth.User;

User user = api.fetchUser();
```

#### Fetching Guilds
Scope `guilds` is required.

```java
import model.xyz.rugman27.discord.oauth.Guild;

List<Guild> guilds = api.fetchGuilds();
```

#### Fetching Connections
Scope `connections` is required.

```java
import model.xyz.rugman27.discord.oauth.Connection;

List<Connection> connections = api.fetchConnections();
```
