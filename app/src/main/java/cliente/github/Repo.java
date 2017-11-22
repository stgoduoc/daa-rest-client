package cliente.github;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zero on 15/11/17.
 */

public class Repo {

    private Long id;
    private String name;
    private String description;
    @SerializedName("html_url")
    private String htmlUrl;

    public Repo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
