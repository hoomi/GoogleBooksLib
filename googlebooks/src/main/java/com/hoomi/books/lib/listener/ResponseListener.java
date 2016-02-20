package com.hoomi.books.lib.listener;

import com.hoomi.books.lib.model.ErrorModel;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public interface ResponseListener<Response> {
    void onSuccess(Response response);

    void onError(ErrorModel errorModel);
}
