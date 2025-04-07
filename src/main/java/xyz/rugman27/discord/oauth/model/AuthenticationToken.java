package xyz.rugman27.discord.oauth.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import xyz.rugman27.discord.oauth.DiscordOAuth;

import java.io.IOException;

@Data
@Setter(AccessLevel.NONE)
public class AuthenticationToken
{
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    private String scope;
    private long expiresAt;

    @Setter
    private DiscordOAuth discordOAuth = null;

    private boolean refreshToken() {
        if(discordOAuth != null) {
            try {
                AuthenticationToken tempToken = getDiscordOAuth().refreshToken(this.refreshToken);
                this.accessToken = tempToken.getAccessToken();
                this.refreshToken = tempToken.getRefreshToken();
                this.expiresIn = tempToken.getExpiresIn();
                this.scope = tempToken.getScope();
                this.expiresAt = tempToken.getExpiresAt();
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private void setExpiresAt() {
        long currentTimestamp = System.currentTimeMillis() / 1000; // current time in seconds
        this.expiresAt = currentTimestamp + this.expiresIn;
    }

    /**
     * Converts a JSON string to an AuthenticationToken object.
     * @param str The JSON string.
     * @return The TokensResponse object.
     */
    public static @NotNull AuthenticationToken fromJson(String str)
    {
        AuthenticationToken authenticationToken = new Gson().fromJson(str, AuthenticationToken.class);
        authenticationToken.setExpiresAt();
        return authenticationToken;
    }

}
