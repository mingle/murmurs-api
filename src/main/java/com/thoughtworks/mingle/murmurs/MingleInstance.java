package com.thoughtworks.mingle.murmurs;

import com.google.common.base.Joiner;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MingleInstance {
    private static final String REGEX = "^(https)://([\\w-]+)\\.([\\w-]+)\\.thoughtworks.com/projects/([\\w]+).*";
    private static final Pattern URL_PATTERN = Pattern.compile(REGEX);

    private static class InvalidMingleInstance extends MingleInstance {
        public InvalidMingleInstance() {
            super(null, null, null, null);
        }
        @Override
        public String getMurmursUrl() {
            return "";
        }

        @Override
        public String getAvatarImageUrl(String imageName) {
            return "";
        }

        public boolean isValid() {
            return false;
        }
    }

    private final String protocol;
    private final String siteName;
    private final String environment;
    private final String projectIdentifier;

    public MingleInstance(String protocol, String siteName, String environment, String projectIdentifier) {
        this.protocol = protocol;
        this.siteName = siteName;
        this.environment = environment;
        this.projectIdentifier = projectIdentifier;
    }

    public boolean isValid() {
        return true;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public static MingleInstance at(String webUrl) {
        Matcher matcher = URL_PATTERN.matcher(webUrl);
        if (matcher.matches()) {
            MatchResult matchResult = matcher.toMatchResult();

            if (matchResult.groupCount() == 4) {
                String protocol = matchResult.group(1);
                String siteName = matchResult.group(2);
                String environment = matchResult.group(3);
                String projectIdentifier = matchResult.group(4);
                return new MingleInstance(protocol, siteName, environment, projectIdentifier);
            }
        }


       return new InvalidMingleInstance();

    }

    public String getMurmursUrl() {
        String baseApiUrl = String.format("%s://%s.%s-api.thoughtworks.com", protocol, siteName, environment);
        return Joiner.on('/').join(baseApiUrl, "api", "v2", "projects", projectIdentifier, "murmurs.xml");
    }

    public String getAvatarImageUrl(String imageName) {
        String baseWebUrl = String.format("%s://%s.%s.thoughtworks.com", protocol, siteName, environment);
        return Joiner.on('/').join(baseWebUrl, "images", "avatars", imageName);
    }

}
