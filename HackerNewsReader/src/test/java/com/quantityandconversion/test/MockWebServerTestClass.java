package com.quantityandconversion.test;

import com.quantityandconversion.hackernews.network.item.internal.ItemNetworkTestResponses;

import org.junit.Before;
import org.junit.Rule;

import okhttp3.mockwebserver.MockWebServer;

public class MockWebServerTestClass {
    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();

    protected ItemNetworkTestResponses itemNetworkTestResponses;
    @Before
    public void setup(){
        itemNetworkTestResponses = new ItemNetworkTestResponses();
    }
}
