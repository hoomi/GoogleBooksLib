package com.hoomi.google.books.mvp.views;

import com.hoomi.books.lib.model.Volume;

import java.util.List;

/**
 * Created by hoomanostovari on 20/02/2016.
 */
public interface VolumeView extends MVPView<List<Volume>> {

    void addVolumes(List<Volume> volumes);
}
