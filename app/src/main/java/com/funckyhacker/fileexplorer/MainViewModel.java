package com.funckyhacker.fileexplorer;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import java.io.File;
import java.util.List;

public abstract class MainViewModel extends BaseObservable {

  public abstract void init(MainView view);

  public abstract void setData(List<File> files);

  @Bindable public abstract String getCurrentPath();

  public abstract int getPageSize();

  public abstract void setCurrentPath(String currentPath);

  public abstract void setFilesToList(@NonNull String name);

  public abstract void setFilesToList(@NonNull File file);

  public abstract void popItem();
}
