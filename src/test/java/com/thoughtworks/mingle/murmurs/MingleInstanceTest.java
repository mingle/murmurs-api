package com.thoughtworks.mingle.murmurs;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MingleInstanceTest {

    private MingleInstance mingle;

    @Before
    public void setup() {
        mingle = MingleInstance.at("https://murmurs-android-test.mingle-staging.thoughtworks.com/projects/test/overview");
        assert(mingle.isValid());
    }

    @Test
    public void shouldParseProtocol() {
        assertEquals("https", mingle.getProtocol());
    }

    @Test
    public void shouldParseSiteName() {
        assertEquals("murmurs-android-test", mingle.getSiteName());
    }

    @Test
    public void shouldParseEnvironment() {
        assertEquals("mingle-staging", mingle.getEnvironment());
    }

    @Test
    public void shouldParseProjectIdentifier() {
        assertEquals("test", mingle.getProjectIdentifier());
    }

    @Test
    public void shouldGenerateCorrectMurmursUrl() {
        assertEquals("https://murmurs-android-test.mingle-staging-api.thoughtworks.com/api/v2/projects/test/murmurs.xml", mingle.getMurmursUrl());
    }

    @Test
    public void shouldGenerateCorrectAvatarImageUrl() {
        assertEquals("https://murmurs-android-test.mingle-staging.thoughtworks.com/images/avatars/image.jpg", mingle.getAvatarImageUrl("image.jpg"));
    }

    @Test
    public void unparsableUrlShouldCreateInvalidInstance() {
        MingleInstance invalid = MingleInstance.at("https://mingle.mingle.thoughtworks.com");
        assertFalse(invalid.isValid());
    }

}
