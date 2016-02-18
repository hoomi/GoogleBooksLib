package com.hoomi.google.books.lib.listener;

import com.hoomi.google.books.lib.model.ErrorModel;
import com.hoomi.google.books.lib.model.Volume;

import java.util.List;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public interface SearchListener {
    void onSuccess(List<Volume> volumes);
    void onError(ErrorModel errorModel);
}
