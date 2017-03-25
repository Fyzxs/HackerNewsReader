package com.quantityandconversion.hackernews.screens.topitems;

import com.quantityandconversion.test.MockWebServerTestClass;
import com.quantityandconversion.test.dialog.FakeDialogBuilder;
import com.quantityandconversion.utils.dialog.AlertDialogBuilderAccess;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesActivityBridgeTests extends MockWebServerTestClass {

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopItemsActivityBridge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topItemsActivity");

        new TopItemsActivityBridge(new TopItemsActivity());
    }

    @Test
    public void loadData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopItemsActivity fakeTopStoriesActivity = new FakeTopItemsActivity(latch);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(fakeTopStoriesActivity);

        topItemsActivityBridge.loadData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test//Flakey?
    public void loadDataError() throws InterruptedException {
        hackerNewsNetworkTestResponses.emptyBodyDataError(mockWebServer);

        final CountDownLatch dialogLatch = new CountDownLatch(1);
        final FakeDialogBuilder fakeDialogBuilder = new FakeDialogBuilder(dialogLatch);
        AlertDialogBuilderAccess.setActiveDialogBuilder(fakeDialogBuilder);

        final FakeTopItemsActivity fakeTopStoriesActivity = new FakeTopItemsActivity(new CountDownLatch(0));
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(fakeTopStoriesActivity);

        topItemsActivityBridge.loadData();

        assertThat(dialogLatch.await(1, TimeUnit.SECONDS)).isTrue();

        fakeDialogBuilder.assertShowCalled(1);
    }

    @Test
    public void notifyTopStoryChanged() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopItemsActivity fakeTopStoriesActivity = new FakeTopItemsActivity(latch);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(fakeTopStoriesActivity);

        topItemsActivityBridge.notifyTopStoryChanged(0);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
    @Test
    public void createTopStoriesAdapter(){
        final FakeTopItemsActivity fakeTopStoriesActivity = new FakeTopItemsActivity(null);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(fakeTopStoriesActivity);

        final TopItemsAdapter adapter = topItemsActivityBridge.createTopStoriesAdapter();

        assertThat(adapter).isNotNull();
    }

    @Test
    public void dataErrorShouldDisplayDialog() throws InterruptedException {
        final CountDownLatch dialogLatch = new CountDownLatch(1);
        final FakeDialogBuilder fakeDialogBuilder = new FakeDialogBuilder(dialogLatch);
        AlertDialogBuilderAccess.setActiveDialogBuilder(fakeDialogBuilder);

        final FakeTopItemsActivity fakeTopStoriesActivity = new FakeTopItemsActivity(null);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(fakeTopStoriesActivity);

        topItemsActivityBridge.dataError().run();

        assertThat(dialogLatch.await(1, TimeUnit.SECONDS)).isTrue();

        fakeDialogBuilder.assertShowCalled(1);
    }

    @Test
    public void dataChangedShouldLoadChangedData() throws InterruptedException{
        final TopItemsActivity mockTopItemsActivity = Mockito.mock(TopItemsActivity.class);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockTopItemsActivity);
        final Runnable dataLoad = topItemsActivityBridge.dataChanged();

        Mockito.doNothing().when(mockTopItemsActivity).notifyTopStoriesChanged();

        dataLoad.run();

        Mockito.verify(mockTopItemsActivity).notifyTopStoriesChanged();
    }

}
