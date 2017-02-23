package com.quantityandconversion.test;

import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetworkTestResponses;

import org.junit.Before;
import org.junit.Rule;

import okhttp3.mockwebserver.MockWebServer;

public abstract class MockWebServerTestClass extends QacTestClass{
    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();

    protected HackerNewsNetworkTestResponses hackerNewsNetworkTestResponses;
    @Before
    public void setup(){
        hackerNewsNetworkTestResponses = new HackerNewsNetworkTestResponses();
    }
}
