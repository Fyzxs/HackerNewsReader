package com.quantityandconversion.utils.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DialogBuilderV7Tests extends QacTestClass {

    @Mock Context mockContext;
    @Mock AlertDialog.Builder mockBuilder;
    @Mock Resources.Theme mockTheme;


    @NonNull
    private DialogBuilderV7 getDialogBuilder() {
        final DialogBuilderV7 v7 = new DialogBuilderV7();
        v7.setBuilder(mockBuilder);
        return v7;
    }

    @Test
    public void init(){
        Mockito.when(mockContext.getTheme()).thenReturn(mockTheme);
        assertThatThrownBy(() -> new DialogBuilderV7().init(mockContext))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Method getSystemService in android.view.ContextThemeWrapper not mocked. See http://g.co/androidstudio/not-mocked for details.");
    }

    @Test
    public void setTitlePassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();
        final int rnd = RandomValues.nextInt(1000);

        v7.setTitle(rnd);

        Mockito.verify(mockBuilder).setTitle(rnd);
    }

    @Test
    public void setTitleFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setTitle(0);

        assertThat(retBuilder).isEqualTo(v7);
    }


    @Test
    public void setMessagePassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();
        final int rnd = RandomValues.nextInt(1000);

        v7.setMessage(rnd);

        Mockito.verify(mockBuilder).setMessage(rnd);
    }

    @Test
    public void setMessageFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setMessage(0);

        assertThat(retBuilder).isEqualTo(v7);
    }


    @Test
    public void setViewPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.setView(null);

        Mockito.verify(mockBuilder).setView(null);
    }

    @Test
    public void setViewFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setView(null);

        assertThat(retBuilder).isEqualTo(v7);
    }

    @Test
    public void setSingleChoiceItemsPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.setSingleChoiceItems(null, 0, null);

        Mockito.verify(mockBuilder).setSingleChoiceItems((CharSequence[])null, 0, null);
    }

    @Test
    public void setSingleChoiceItemsFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setSingleChoiceItems(null, 0, null);

        assertThat(retBuilder).isEqualTo(v7);
    }

    @Test
    public void setNegativeButtonPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.setNegativeButton(0, null);

        Mockito.verify(mockBuilder).setNegativeButton(0, null);
    }

    @Test
    public void setNegativeButtonFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setNegativeButton(0, null);

        assertThat(retBuilder).isEqualTo(v7);
    }

    @Test
    public void setPositiveButtonPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.setPositiveButton(0, null);

        Mockito.verify(mockBuilder).setPositiveButton(0, null);
    }

    @Test
    public void setPositiveButtonFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setPositiveButton(0, null);

        assertThat(retBuilder).isEqualTo(v7);
    }

    @Test
    public void setNeutralButtonPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.setNeutralButton(0, null);

        Mockito.verify(mockBuilder).setNeutralButton(0, null);
    }

    @Test
    public void setNeutralButtonFluent(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        final DialogBuilder retBuilder = v7.setNeutralButton(0, null);

        assertThat(retBuilder).isEqualTo(v7);
    }

    @Test
    public void createPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.create();

        Mockito.verify(mockBuilder).create();
    }

    @Test
    public void showPassesThrough(){
        final DialogBuilderV7 v7 = getDialogBuilder();

        v7.show();

        Mockito.verify(mockBuilder).show();
    }
}
