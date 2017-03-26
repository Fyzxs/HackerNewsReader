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

public class TopItemsActivityBridgeTests extends MockWebServerTestClass {

    @Test
    public void constructorThrowsGivenNull(){

        assertThatThrownBy(() -> new TopItemsActivityBridge((TopItemsActivityBridge.UiView)null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topItemsActivity");

    }

    @Test
    public void ctorTakesDisplay() {
       new TopItemsActivityBridge(Mockito.mock(TopItemsActivityBridge.UiView.class));
    }

    @Test
    public void loadData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        Mockito.doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(mockUiView).notifyTopStoriesChanged();
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);


        topItemsActivityBridge.loadData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test//Flakey?
    public void loadDataError() throws InterruptedException {
        hackerNewsNetworkTestResponses.emptyBodyDataError(mockWebServer);

        final CountDownLatch dialogLatch = new CountDownLatch(1);
        final FakeDialogBuilder fakeDialogBuilder = new FakeDialogBuilder(dialogLatch);
        AlertDialogBuilderAccess.setActiveDialogBuilder(fakeDialogBuilder);

        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);

        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);

        topItemsActivityBridge.loadData();

        assertThat(dialogLatch.await(1, TimeUnit.SECONDS)).isTrue();

        fakeDialogBuilder.assertShowCalled(1);
    }

    @Test
    public void notifyTopStoryChanged() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        Mockito.doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(mockUiView).notifyTopStoryChanged(0);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);

        topItemsActivityBridge.notifyTopStoryChanged(0);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
    @Test
    public void createTopStoriesAdapter(){
        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);

        final TopItemsAdapter adapter = topItemsActivityBridge.createTopStoriesAdapter();

        assertThat(adapter).isNotNull();
    }

    @Test
    public void dataErrorShouldDisplayDialog() throws InterruptedException {
        final CountDownLatch dialogLatch = new CountDownLatch(1);
        final FakeDialogBuilder fakeDialogBuilder = new FakeDialogBuilder(dialogLatch);
        AlertDialogBuilderAccess.setActiveDialogBuilder(fakeDialogBuilder);

        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);

        topItemsActivityBridge.dataError().run();

        assertThat(dialogLatch.await(1, TimeUnit.SECONDS)).isTrue();

        fakeDialogBuilder.assertShowCalled(1);
    }

    @Test
    public void dataChangedShouldLoadChangedData() throws InterruptedException{
        final TopItemsActivityBridge.UiView mockUiView = Mockito.mock(TopItemsActivityBridge.UiView.class);
        final TopItemsActivityBridge topItemsActivityBridge = new TopItemsActivityBridge(mockUiView);
        final Runnable dataLoad = topItemsActivityBridge.dataChanged();

        Mockito.doNothing().when(mockUiView).notifyTopStoriesChanged();

        dataLoad.run();

        Mockito.verify(mockUiView).notifyTopStoriesChanged();
    }

}
