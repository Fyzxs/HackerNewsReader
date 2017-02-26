package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.dialog.FakeDialogBuilder;
import com.quantityandconversion.utils.dialog.AlertDialogBuilderAccess;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TopStoriesActivityBridgeDataLoadStrategyTests extends QacTestClass {

    @Mock TopStoriesActivity mockTopStoriesActivity;

    @Test
    public void dataChanged(){
        Mockito.doNothing().when(mockTopStoriesActivity).notifyTopStoriesChanged();

        TopStoriesActivityBridge.DataLoadStrategy.DataChanged.run(new TopStoriesActivityBridge(mockTopStoriesActivity));

        Mockito.verify(mockTopStoriesActivity).notifyTopStoriesChanged();
    }

    @Test
    public void dataError() throws InterruptedException {
        final CountDownLatch dialogLatch = new CountDownLatch(1);
        final FakeDialogBuilder fakeDialogBuilder = new FakeDialogBuilder(dialogLatch);
        AlertDialogBuilderAccess.setActiveDialogBuilder(fakeDialogBuilder);
        TopStoriesActivityBridge.DataLoadStrategy.DataError.run(new TopStoriesActivityBridge(mockTopStoriesActivity));

        assertThat(dialogLatch.await(1, TimeUnit.SECONDS)).isTrue();

        fakeDialogBuilder.assertShowCalled(1);
    }
}
